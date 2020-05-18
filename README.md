# java-h2-master
测试步骤：

1.新增数据接口

url：http://localhost:8080/demo/saveTransactions
method:Post
param（放于Body,选择raw,Json(application/json)）:
    {
    "securityCode":"REL",
    "quantity":50,
    "operation":"INSERT",
    "type":"Buy"
    }
点击send按钮，响应框返回"插入成功"。

同样操作，放入其他剩余参数
{
"securityCode":"ITC",
"quantity":40,
"operation":"INSERT",
"type":"Sell"
}

{
"securityCode":"INF",
"quantity":70,
"operation":"INSERT",
"type":"Buy"
}

{
"securityCode":"REL",
"quantity":60,
"operation":"UPDATE",
"type":"Buy"
}

{
"securityCode":"ITC",
"quantity":30,
"operation":"CANCEL",
"type":"Buy"
}

{
"securityCode":"INF",
"quantity":20,
"operation":"INSERT",
"type":"Sell"
}


2.查询插入的数据
url：http://localhost:8080/demo/queryTransactionsDetail
method:Get
点击send按钮，响应框返回列表。


3.查询计算结果
url：http://localhost:8080/demo/findAllSecurityInfo
method:Get
点击send按钮，响应框返回列表。









