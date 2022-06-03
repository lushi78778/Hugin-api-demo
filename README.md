# Hugin api使用

## 概述

Hugin Expert，一个关于贝叶斯网络的软件，提供c、c++、java、.net的api支持，并且有免费的Hugin lite使用。
它的贝叶斯网络支持离散和连续的节点，支持表达式和高斯分布。

<li>Domain类，是指一个贝叶斯网络，可以通过getNodes()方法获得其中的所有节点。</li>
<li>Node代表贝叶斯网络中的一个节点，它是一个基类，子类包括ContinuousChanceNode, DiscreteNode, InstanceNode, UtilityNode。</li>
<li>ContinuousChanceNode是连续节点，DiscreteNode是离散节点，UtilityNode是工具节点，InstanceNode不知道。</li>
<li>DiscreteNode还有子类DiscreteChanceNode, DiscreteDecisionNode，常用的是DiscreteChanceNode。</li>

## 参考资料

maven参考：

https://www.cnblogs.com/chywx/p/11563318.html
https://www.cnblogs.com/Fooo/p/15605972.html

api说明手册：

https://download.hugin.com/webdocs/manuals/api-manual.pdf

hugin下载地址：

https://www.hugin.com/hugin-lite/

hugin文档：

https://download.hugin.com/webdocs/manuals/Java/

（.net文档相对详细，有例子可以去看，.net也很简单的）

hugin介绍：

https://blog.csdn.net/weixin_34275734/article/details/85424516