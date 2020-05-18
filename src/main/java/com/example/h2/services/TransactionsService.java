package com.example.h2.services;

import com.example.h2.entity.SecurityInfo;
import com.example.h2.entity.TradeInfo;
import com.example.h2.entity.Transactions;
import com.example.h2.enums.BuyOrSellTypeEnum;
import com.example.h2.enums.OperatinTypeEnum;
import com.example.h2.repository.SecurityInfoRepository;
import com.example.h2.repository.TradeInfoRepository;
import com.example.h2.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private TradeInfoRepository tradeInfoRepository;

    @Autowired
    SecurityInfoService securityInfoService;

    @Autowired
    private SecurityInfoRepository securityInfoRepository;

    public static Integer BEGINNUMBER = 1;
    public static Integer ENDNUMBER = 2;

    @Transactional
    public String saveTransactions(Transactions model) {

        List<SecurityInfo> list = dealListSecurityInfo(model);

        List<Transactions> repositoryAll = transactionsRepository.findAll();

        //版本号
        model.setVersion(BEGINNUMBER);

        //数量处理
        Integer result = getQuantityByType(model);

        //判断操作类型 - 新增
        if (model.getOperation().equals(OperatinTypeEnum.INSERT.getCode())) {

            if (list.size() == 0) {
                //获取交易号
                TradeInfo info = this.getTradeNumber();
                model.setTraeId(info.getNumber());

                SecurityInfo securityInfo = new SecurityInfo();
                securityInfo.setSecurityCode(model.getSecurityCode());
                securityInfo.setQuantity(result);
                securityInfoService.save(securityInfo);
            } else {
                //判断流水状态，如果已经有更新或者取消状态，则跳过数量变更
                Integer res = dealTransactionsInfo(model,result,list,repositoryAll);
                //交易号处理
                model.setTraeId(res);
            }
        }

        //判断操作类型 - 更新
        if (model.getOperation().equals(OperatinTypeEnum.UPDATE.getCode())) {
            //securityCode找到当前条数，确认版本号
            dealVersionId(model);
            //获取交易号
            model.setTraeId(getTractIdByCode(model,repositoryAll));
            if (list.size()>0) {
                //更新securityInfo
                securityInfoService.updateSecurityInfo(model.getQuantity(), list.get(0).getId());
            }
            if (list.size()==0){
                SecurityInfo securityInfo = new SecurityInfo();
                securityInfo.setSecurityCode(model.getSecurityCode());
                securityInfo.setQuantity(result);
                securityInfoService.save(securityInfo);
            }

        }

        //判断操作类型 -取消
        if (model.getOperation().equals(OperatinTypeEnum.CANCLE.getCode())) {
            //获取交易号及securityCode找到当前条数，确认版本号
            dealVersionId(model);
            //获取流水号
            model.setTraeId(getTractIdByCode(model,repositoryAll));
            //更新securityInfo
            if (list.size()>0) {
                securityInfoService.updateSecurityInfo(0, list.get(0).getId());
            }
            if (list.size()==0){
                SecurityInfo securityInfo = new SecurityInfo();
                securityInfo.setSecurityCode(model.getSecurityCode());
                securityInfo.setQuantity(0);
                securityInfoService.save(securityInfo);
            }
        }

        //判断是否重复插入
        if (dealIsAlreadyExist(model, repositoryAll)) return "该数据已存在";

        //新增记录
        Transactions save = transactionsRepository.save(model);
        return "插入成功";
    }

    private boolean dealIsAlreadyExist(Transactions model, List<Transactions> repositoryAll) {
        for (Transactions transactionsData : repositoryAll) {
            if (transactionsData.getTraeId() == model.getTraeId()
                            && transactionsData.getSecurityCode().equals(model.getSecurityCode())
                            && transactionsData.getOperation().equals(model.getOperation())
            ) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断同code流水中是否有取消和插入
     * */
    private Integer dealTransactionsInfo(Transactions model,Integer result,List<SecurityInfo> list,List<Transactions> repositoryAll) {
        List<Transactions> resultReady = new ArrayList<>();
        for (Transactions transactionOne:repositoryAll){
            if (transactionOne.getSecurityCode().equals(model.getSecurityCode())){
                if (transactionOne.getOperation().equals(OperatinTypeEnum.UPDATE.getCode())
                        || transactionOne.getOperation().equals(OperatinTypeEnum.CANCLE.getCode())){
                    resultReady.add(transactionOne);
                }
            }
        }
        if (resultReady.size()>0){
            return resultReady.get(0).getTraeId();
        }
        Integer quantity = new Integer(list.get(0).getQuantity() + result);
        securityInfoService.updateSecurityInfo(quantity, list.get(0).getId());
        return this.getTradeNumber().getNumber();
    }


    private Integer getTractIdByCode(Transactions model,List<Transactions> listTransactions) {
        List<Transactions> result = new ArrayList<>();
        for (Transactions transactions : listTransactions) {
            if (model.getSecurityCode().equals(transactions.getSecurityCode())) {
                result.add(transactions);
            }
        }
        if (result.size()==0){
            return this.getTradeNumber().getNumber();
        }
        return result.get(0).getTraeId();
    }

    private List<SecurityInfo> dealListSecurityInfo(Transactions model) {
        List<SecurityInfo> infoRepositoryAll = securityInfoRepository.findAll();
        List<SecurityInfo> list = new ArrayList<>();
        for (SecurityInfo infoRe : infoRepositoryAll) {
            if (infoRe.getSecurityCode().equals(model.getSecurityCode())) {
                list.add(infoRe);
            }
        }
        return list;
    }


    private void dealVersionId(Transactions model) {
        List<Transactions> list =
                transactionsRepository.queryBySecurityCode(model.getSecurityCode());
        if (list.size()>0) {
            model.setVersion(new Integer(list.size() + 1));
        }else {
            model.setVersion(ENDNUMBER);
        }
    }

    private Integer getQuantityByType(Transactions model) {
        Integer result = 0;
        if (model.getType().equals(BuyOrSellTypeEnum.BUY.getCode())) {
            result = model.getQuantity();
        }
        if (model.getType().equals(BuyOrSellTypeEnum.SELL.getCode())) {
            result = new Integer(model.getQuantity() * (-1));
        }
        return result;
    }


    public TradeInfo getTradeNumber() {
        //初始化数据库，判断库中是否有数据
        List<TradeInfo> all = tradeInfoRepository.findAll();

        if (all.size() == 0) {
            TradeInfo tradeInfo = new TradeInfo();
            tradeInfo.setNumber(BEGINNUMBER);
            TradeInfo save = tradeInfoRepository.save(tradeInfo);
            return save;
        } else {
            TradeInfo tradeInfo = all.get(0);
            tradeInfo.setNumber(all.get(0).getNumber() + 1);

            //更新库中交易号
            tradeInfoRepository.update(tradeInfo.getNumber(), tradeInfo.getId());
            return tradeInfo;
        }
    }

    public List<Transactions> queryTransactionsDetail() {
        return transactionsRepository.findAll();
    }
}
