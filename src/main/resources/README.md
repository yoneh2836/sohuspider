爬取目标：sohu

爬取类容：新闻url，新闻标题，作者，时间

技术选型：HttpClient，HtmlCleaner，Jsoup，XPath，正则表达式，mysql，mybatis，Redis

思路：httpClient用于请求url得到页面；HtmlCleaner、Jsoup、XPath、正则表达式对请求得到的页面进行解析（详情页与非详情页），mysql中存解析得到的新闻信息与url（用status字段标识已解析或未解析）两张表都以MD5(url)为主键，并对新闻信息分表存储；Redis缓存已解析的url

流程图：

![image-20191202102438309](C:\Uimage-20191202102438309.png)sers\Administrator\AppData\Roaming\Typora\typora-user-images\