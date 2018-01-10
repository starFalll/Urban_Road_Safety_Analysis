<%@ page import="team.web_first.javabean.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    /**
     * 获得session用户对象
     * */
    User user;
    user = (User) request.getSession().getAttribute("user");
%>

<html>
<head>
    <title>Home</title>
    <meta content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"
          name="viewport"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="shortcut icon" href="../images/title.png">
    <link rel="icon" href="../images/title.png">
    <link rel="Bookmark" href="../images/title.png">
    <link
            href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
            rel='stylesheet' type='text/css'>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcss.com/echarts/3.8.5/echarts.min.js"></script>
    <script>
        $(window).on('load', function () {
            window.scrollTo(0, 1);
            document.addEventListener('touchstart', function (event) {
                if (event.touches.length > 1) {
                    event.preventDefault();
                }
            })
            var lastTouchEnd = 0;
            document.addEventListener('touchend', function (event) {
                var now = (new Date()).getTime();
                if (now - lastTouchEnd <= 300) {
                    event.preventDefault();
                }
                lastTouchEnd = now;
            }, false)
        });
    </script>
</head>
<body>
<div class="container">
    <div class="navigation">
        <div class="logo text-center">
            <h1>城市道路分析管理系统</h1>
        </div>
        <nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#example-navbar-collapse">
                        <span class="sr-only">切换导航</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <p class="navbar-brand"><span class="glyphicon glyphicon-chevron-right"></span></p>
                </div>
                <div class="collapse navbar-collapse" id="example-navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="active display">
                            <a href="javascript:void(0)" onclick="display();toggleBar();">
                                首页
                            </a>
                        </li>
                        <li class="analysis">
                            <a href="javascript:void(0)" onclick="analysis();toggleBar();">
                                统计分析
                            </a>
                        </li>
                        <li class="persAnalysis">
                            <a href="javascript:void(0)" onclick="persAnalysis();toggleBar();">
                                个人分析
                            </a>
                        </li>
                        <li class="quest">
                            <a href="javascript:void(0)" onclick="quest();toggleBar();">
                                问卷调查
                            </a>
                        </li>
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button"
                               aria-haspopup="true" aria-expanded="false"> <span
                                    class="glyphicon glyphicon-user"></span> <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a><span class="glyphicon glyphicon-user"></span><%= user.getUserName()%>
                                </a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="LogoutServlet"><span
                                        class="glyphicon glyphicon-log-out"></span>Log
                                    out</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
    <div class="display-content">
        <div class="jumbotron">
            <h1>交通安全的重要性</h1>
            <br/>
            <p>年末全国民用汽车保有量19440万辆（包括三轮汽车和低速货车881万辆），
                比上年末增长12.8%，其中私人汽车保有量16559万辆，增长15.0%。民用轿车保有量10876万辆，
                增长14.4%，其中私人轿车10152万辆，增长15.5%。</p>
        </div>
        <div class="bar-content">
            <div class="bar" id="bar4"></div>
        </div>
        <div class="pie-content">
            <div class="pie" id="pie"></div>
        </div>
        <div class="bar-content">
            <div class="bar" id="bar5"></div>
        </div>
    </div>
    <div style="display: none" class="analysis-content">
        <div class="jumbotron">
            <h1>多因素统计分析图</h1>
            <br/>
            <p>
                横轴表示四种不同的因素，纵轴表示一种因素和另外三种因素之间的相关系数，相关系数越高，两种因素相互影响越大。
                三因素系数图中，横轴表示两种因素对第三种因素，纵轴表示三因素的相关系数，相关系数越大，这两种因素对第三种因素的影响越大。
            </p>
        </div>
        <div class="introduction1" id="introduction1">
            <div class="colla-intro1">
                <a class="toggle-intro btn btn-inverse btn-block" data-toggle="collapse" data-parent="#introduction1"
                   href="#intro1-content">
                    显示说明
                </a>
            </div>
            <div class="intro1-content" id="intro1-content">
                <h1>调查问卷一共有四种内容，分别来自于</h1>
                <h3>
                    <ul>
                        <li>人格特性问卷测评部分基于Costa等人(1992)提出的NEO-PI-R 人格量表修正而得</li>
                        <li>风险感知问卷测评部分基于Noland(1995)提出的风险感知量表</li>
                        <li>驾驶能力自信问卷测评部分基于Lajunen 与Summala(1995)设计的驾驶能力量表(DSI)</li>
                        <li>危险驾驶行为问卷测评部分基于Reason 等人(1990)提出的驾驶行为问卷(DBQ)</li>
                    </ul>
                </h3>
            </div>
            <hr/>
            <h1><br/>多因素统计分析图<br/></h1>
        </div>
        <div class="bar-content">
            <div class="bar" id="bar2"></div>
        </div>
        <div class="introduction3">
            <hr/>
            <div class="intro3-header ">
                <h1>在三因素系数图中</h1>
            </div>
            <div class="intro3-content ">
                <h3><span>A</span>为道路风险感知能力</h3>
                <h3><span>B</span>为危险驾驶行为</h3>
                <h3><span>C</span>为驾驶能力自信</h3>
                <h3><span>D</span>为人格特性</h3>
                <h3 style="margin-left: 12%">例如，AB->C表示A,B对C的影响系数</h3>
            </div>
        </div>
        <div class="bar-content">
            <div class="bar" id="bar3"></div>
        </div>
    </div>
    <div style="display: none" class="persAnalysis-content">
        <div class="jumbotron">
            <h1>个人分析</h1>
            <br/>
            <p>
                通过关联规则挖掘算法对大量问卷结果的处理，得出以下结果:
            <ul>
                <li>危险驾驶行为得分体现出驾车出现危险的可能性，得分越高，越安全</li>
                <li>道路风险感知能力得分体现出对路况的适应能力和处理突发事件的能力，得分越高，能力越强</li>
                <li>驾驶能力自信得分体现出驾车的自信度，得分越高，开车时越自信</li>
            </ul>
            </p>
        </div>
        <div class="bar-content">
            <div class="bar" id="bar1"></div>
        </div>
        <br/>
        <hr/>
        <div class="intro4-content">
            <ul>
                <li id="pA"></li>
                <li id="pD"></li>
                <li id="pC"></li>
            </ul>
        </div>
        <br/>
        <div class="pie-content">
            <div class="pie" id="pie2"></div>
        </div>
    </div>
    <div style="display: none" class="quest-panel panel panel-default">
        <div class="panel-heading">
            <h2>Questionnaire</h2>
        </div>
        <div class="question">
            <div><h3 class="text-center quest-text"></h3></div>
            <div class="quest-option">
                <button class="btn btn-lg btn-primary pull-left" type="button" name="quest-not" id="quest-not" value="">
                    否
                </button>
                <button class="btn btn-lg btn-success pull-right" type="button" name="quest-is" id="quest-is" value="">
                    是
                </button>
            </div>
        </div>
    </div>
    <div class="clearfix"></div>
</div>
<script>

    //绘制图表
    var bar1Chart = echarts.init(document.getElementById('bar1'));
    var bar2Chart = echarts.init(document.getElementById('bar2'));
    var bar3Chart = echarts.init(document.getElementById('bar3'));
    var bar4Chart = echarts.init(document.getElementById('bar4'));
    var bar5Chart = echarts.init(document.getElementById('bar5'));
    var pieChart = echarts.init(document.getElementById('pie'));
    var pie2Chart = echarts.init(document.getElementById('pie2'));
    //bar1
    $(function () {
        bar1Chart.showLoading();
        $.ajax(
            {
                url: "/Urban_Road_Safety_Analysis/PersResultServlet",
                success: function (data) {
                    var persResult = JSON.parse(data);
                    bar1Chart.hideLoading();
                    bar1Chart.setOption({
                        backgroundColor: '#1A1A1A',
                        title: {
                            text: '个人测试得分'.split("").join("\n"),
                            textStyle: {
                                shadowColor: '#000000',
                                shadowBlur: 5,
                                shadowOffsetX: 3,
                                shadowOffsetY: 3,
                                color: '#e4e4e4',
                                fontSize: 17
                            },
                            top: '36%',
                            left: '4%'
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {
                                type: 'shadow'
                            }
                        },
                        grid: {
                            top: '10%',
                            left: '9%',
                            right: '10%',
                            bottom: '1%',
                            containLabel: true,
                        },
                        textStyle: {
                            shadowColor: '#000000',
                            shadowBlur: 5,
                            shadowOffsetX: 3,
                            shadowOffsetY: 3,
                            color: '#e4e4e4'
                        },
                        xAxis: {
                            type: 'category',
                            data: ['道路风险感知能力', '危险驾驶行为', '驾驶自信度'],
                            axisLabel: {
                                interval: 0,
                                formatter: function (value) {
                                    var ret = "";//拼接加\n返回的类目项
                                    var maxLength = 4;//每项显示文字个数
                                    var valLength = value.length;//X轴类目项的文字个数
                                    var rowN = Math.ceil(valLength / maxLength); //类目项需要换行的行数
                                    if (rowN > 1)//如果类目项的文字大于3,
                                    {
                                        for (var i = 0; i < rowN; i++) {
                                            var temp = "";//每次截取的字符串
                                            var start = i * maxLength;//开始截取的位置
                                            var end = start + maxLength;//结束截取的位置
                                            //这里也可以加一个是否是最后一行的判断，但是不加也没有影响，那就不加吧
                                            temp = value.substring(start, end) + "\n";
                                            ret += temp; //凭借最终的字符串
                                        }
                                        return ret;
                                    }
                                    else {
                                        return value;
                                    }
                                }
                            }
                        },
                        yAxis: {
                            type: 'value',
                            min: 0,
                            max: 100,
                            minInterval: 30,
                            maxInterval: 30,
                            axisLabel: {
                                formatter: function (value, index) {
                                    switch (index) {
                                        case 1:
                                            return '待加强\n30';
                                            break;
                                        case 2:
                                            return '良好\n60';
                                            break;
                                        case 3:
                                            return '安全\n90';
                                            break;
                                        default:
                                            return value;
                                    }
                                }
                            },
                            splitLine: {
                                lineStyle: {
                                    width: 1.5,
                                    color: ['#b0afb3', '#950017', '#a7603d', '#37a82f', '#b0afb3']
                                }
                            }
                        },
                        series: [
                            {
                                name: '得分',
                                type: 'bar',
                                data: [persResult.abiOneScore, persResult.abiTwoScore, persResult.abiThrScore],
                                label: {
                                    normal:
                                        {
                                            show: true,
                                            position: 'top',
                                            distance: 20,
                                            fontSize: 16,
                                            lineHeight: 70,
                                            formatter: function (params) {
                                                var list = ['安全', '良好', '较危险', '很危险'];
                                                if (params.dataIndex == 0) {
                                                    return list[persResult.oneDegree] + '\n' + persResult.abiOneScore;
                                                }
                                                else if (params.dataIndex == 1) {
                                                    return list[persResult.twoDegree] + '\n' + persResult.abiTwoScore;
                                                }
                                                else {
                                                    return list[persResult.thrDegree] + '\n' + persResult.abiThrScore;
                                                }
                                            }
                                        }
                                },
                                itemStyle: {
                                    normal: {
                                        color: function (params) {
                                            var colorList = ['#51616d', '#003336', "#6D615B"];
                                            return colorList[params.dataIndex];
                                        }
                                    }
                                },
                                barCategoryGap: '60%',
                            },
                            {
                                name: '得分',
                                type: 'line',
                                itemStyle: {
                                    normal: {
                                        color: '#dcdcdc'
                                    }
                                },
                                data: [persResult.abiOneScore, persResult.abiTwoScore, persResult.abiThrScore],
                            }
                        ]

                    })
                    ;
                },
                error: function (data) {
                    bar1Chart.hideLoading();
                    bar1Chart.setOption({
                        backgroundColor: '#1A1A1A',
                        title: {
                            text: '请先完成个人测试',
                            textStyle: {
                                shadowColor: '#000000',
                                shadowBlur: 5,
                                shadowOffsetX: 3,
                                shadowOffsetY: 3,
                                color: '#e4e4e4',
                                fontSize: 17,
                            },
                            top: '2%',
                            left: '43%'
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {
                                type: 'shadow'
                            }
                        },
                        textStyle: {
                            shadowColor: '#000000',
                            shadowBlur: 5,
                            shadowOffsetX: 3,
                            shadowOffsetY: 3,
                            color: '#e4e4e4',
                        },
                        xAxis: {
                            type: 'category',
                            data: ['道路风险感知能力', '危险驾驶行为'],
                            axisLabel: {
                                interval: 0,
                                formatter: function (value) {
                                    var ret = "";//拼接加\n返回的类目项
                                    var maxLength = 4;//每项显示文字个数
                                    var valLength = value.length;//X轴类目项的文字个数
                                    var rowN = Math.ceil(valLength / maxLength); //类目项需要换行的行数
                                    if (rowN > 1)//如果类目项的文字大于3,
                                    {
                                        for (var i = 0; i < rowN; i++) {
                                            var temp = "";//每次截取的字符串
                                            var start = i * maxLength;//开始截取的位置
                                            var end = start + maxLength;//结束截取的位置
                                            //这里也可以加一个是否是最后一行的判断，但是不加也没有影响，那就不加吧
                                            temp = value.substring(start, end) + "\n";
                                            ret += temp; //凭借最终的字符串
                                        }
                                        return ret;
                                    }
                                    else {
                                        return value;
                                    }
                                }
                            }
                        },
                        yAxis: {
                            type: 'value'
                        }
                    });
                    $("#bar1").attr("onclick", "quest()");
                }
            }
        )
    });
    //ba2
    $(function () {
        /**
         * charts variable
         * X axis = ['危险驾驶行为', '驾驶能力自信', '人格特性', '道路风险感知能力']
         * datas[?] ? = X 下标
         * */
        var maps = ['危险驾驶行为', '驾驶能力自信', '人格特性', '道路风险感知能力'];
        var datas = new Array();
        datas[0] = new Array();
        datas[1] = new Array();
        datas[2] = new Array();
        datas[3] = new Array();
        var results;
        bar2Chart.showLoading();
        $.ajax({
            url: "/Urban_Road_Safety_Analysis/ResultServlet",
            success: function (data) {
                results = JSON.parse(data);
                for (var i = 0; i < 4; i++) {
                    for (var j = 0; j < 4; j++) {
                        if (i != j) {
                            for (var k = 0, len = results.length; k < len; k++) {
                                if (results[k].name1 == maps[i] && results[k].name2 == maps[j]) {
                                    datas[j][i] = results[k].confidence;
                                    datas[i][j] = results[k].confidence;
                                }
                                if (results[k].name1 == maps[j] && results[k].name2 == maps[i]) {
                                    datas[j][i] = results[k].confidence;
                                    datas[i][j] = results[k].confidence;
                                }
                            }
                        }
                        else {
                            datas[i][j] = '';
                        }
                    }
                }
                bar2Chart.hideLoading();
                bar2Chart.setOption(
                    {
                        backgroundColor: '#1A1A1A',
                        title: {
                            text: '两因素相关系数'.split("").join("\n"),
                            textStyle: {
                                shadowColor: '#000000',
                                shadowBlur: 5,
                                shadowOffsetX: 3,
                                shadowOffsetY: 3,
                                color: '#e4e4e4',

                            },
                            top: '35%',
                            left: '2%'
                        },
                        textStyle: {
                            shadowColor: '#000000',
                            shadowBlur: 5,
                            shadowOffsetX: 3,
                            shadowOffsetY: 3,
                            color: '#e4e4e4',
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {type: 'shadow'}
                        },
                        legend: {
                            top: '3%',
                            textStyle: {
                                shadowColor: '#000000',
                                shadowBlur: 5,
                                shadowOffsetX: 3,
                                shadowOffsetY: 3,
                                color: '#e4e4e4',
                            },
                            data: ['人格特性', '驾驶能力自信', '危险驾驶行为', '道路风险感知能力']
                        },
                        grid: {
                            top: '20%',
                            left: '10%',
                            right: '10%',
                            bottom: '1%',
                            containLabel: true
                        },
                        xAxis: {
                            type: 'category',
                            data: ['危险驾驶行为', '驾驶能力自信', '人格特性', '道路风险感知能力'],
                            axisLabel: {
                                interval: 0,
                                formatter: function (value) {
                                    var ret = "";//拼接加\n返回的类目项
                                    var maxLength = 2;//每项显示文字个数
                                    var valLength = value.length;//X轴类目项的文字个数
                                    var rowN = Math.ceil(valLength / maxLength); //类目项需要换行的行数
                                    if (rowN > 1)//如果类目项的文字大于3,
                                    {
                                        for (var i = 0; i < rowN; i++) {
                                            var temp = "";//每次截取的字符串
                                            var start = i * maxLength;//开始截取的位置
                                            var end = start + maxLength;//结束截取的位置
                                            //这里也可以加一个是否是最后一行的判断，但是不加也没有影响，那就不加吧
                                            temp = value.substring(start, end) + "\n";
                                            ret += temp; //凭借最终的字符串
                                        }
                                        return ret;
                                    }
                                    else {
                                        return value;
                                    }
                                }
                            }
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [
                            {
                                name: '危险驾驶行为',
                                type: 'bar',
                                stack: '系数',
                                label: {
                                    normal: {
                                        show: true,
                                    }
                                },
                                data: datas[0],
                                itemStyle: {
                                    normal: {color: '#003336'}
                                }
                            }, {
                                name: '人格特性',
                                type: 'bar',
                                stack: '系数',
                                label: {
                                    normal: {
                                        show: true,
                                    }
                                },
                                data: datas[2],
                                itemStyle: {
                                    normal: {color: '#e79169'}
                                }
                            }, {
                                name: '驾驶能力自信',
                                type: 'bar',
                                stack: '系数',
                                label: {
                                    normal: {
                                        show: true,
                                    }
                                },
                                data: datas[1],
                                itemStyle: {
                                    normal: {color: '#51616d'}
                                }
                            }, {
                                name: '道路风险感知能力',
                                type: 'bar',
                                stack: '系数',
                                label: {
                                    normal: {
                                        show: true,
                                    }
                                },
                                barCategoryGap: '40%',
                                data: datas[3],
                                itemStyle: {
                                    normal: {color: '#77a8ad'}
                                }
                            }
                        ]
                    }
                );
            },
            error: function (data) {
                bar2Chart.hideLoading();
                bar2Chart.setOption(
                    {
                        backgroundColor: '#1A1A1A',
                        title: {
                            text: 'Load Fail',
                            textStyle: {
                                shadowColor: '#000000',
                                shadowBlur: 5,
                                shadowOffsetX: 3,
                                shadowOffsetY: 3,
                                color: '#e4e4e4',

                            },
                            top: '2%',
                            left: '43%',
                        },
                        textStyle: {
                            shadowColor: '#000000',
                            shadowBlur: 5,
                            shadowOffsetX: 3,
                            shadowOffsetY: 3,
                            color: '#e4e4e4',
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {type: 'shadow'}
                        },
                        grid: {
                            top: '20%',
                            left: '10%',
                            right: '10%',
                            bottom: '1%',
                            containLabel: true
                        },
                        xAxis: {
                            type: 'category',
                            data: ['危险驾驶行为', '驾驶能力自信', '人格特性', '道路风险感知能力'],
                            axisLabel: {
                                interval: 0,
                                formatter: function (value) {
                                    var ret = "";//拼接加\n返回的类目项
                                    var maxLength = 2;//每项显示文字个数
                                    var valLength = value.length;//X轴类目项的文字个数
                                    var rowN = Math.ceil(valLength / maxLength); //类目项需要换行的行数
                                    if (rowN > 1)//如果类目项的文字大于3,
                                    {
                                        for (var i = 0; i < rowN; i++) {
                                            var temp = "";//每次截取的字符串
                                            var start = i * maxLength;//开始截取的位置
                                            var end = start + maxLength;//结束截取的位置
                                            //这里也可以加一个是否是最后一行的判断，但是不加也没有影响，那就不加吧
                                            temp = value.substring(start, end) + "\n";
                                            ret += temp; //凭借最终的字符串
                                        }
                                        return ret;
                                    }
                                    else {
                                        return value;
                                    }
                                }
                            }
                        },
                        yAxis: {
                            type: 'value'
                        },

                    }
                );
            },
        });
    });
    //bar3
    $(function () {
        bar3Chart.showLoading();
        $.ajax({
            url: "/Urban_Road_Safety_Analysis/Result2Servlet",
            success: function (data) {
                var results = JSON.parse(data);
                bar3Chart.hideLoading();
                bar3Chart.setOption(
                    {
                        backgroundColor: '#1A1A1A',
                        title: {
                            text: '三因素相关系数'.split("").join("\n"),
                            textStyle: {
                                shadowColor: '#000000',
                                shadowBlur: 5,
                                shadowOffsetX: 3,
                                shadowOffsetY: 3,
                                color: '#e4e4e4',
                            },
                            top: '20%',
                            left: '2%'
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {type: 'shadow'}
                        },
                        grid: {
                            top: '10%',
                            left: '10%',
                            right: '10%',
                            bottom: '20%',
                            containLabel: true
                        },
                        textStyle: {
                            shadowColor: '#000000',
                            shadowBlur: 5,
                            shadowOffsetX: 3,
                            shadowOffsetY: 3,
                            color: '#e4e4e4',
                        },
                        xAxis: {
                            type: 'category',
                            data: [
                                'BC\n↓\nA', 'BD\n↓\nA', 'CD\n↓\nA',
                                'AC\n↓\nB', 'AD\n↓\nB', 'CD\n↓\nB',
                                'AB\n↓\nC', 'AD\n↓\nC', 'BD\n↓\nC',
                                'AB\n↓\nD', 'AC\n↓\nD', 'BC\n↓\nD'
                            ],
                            axisLabel: {
                                interval: 0,
                                formatter: function (value) {
                                    var ret = "";//拼接加\n返回的类目项
                                    var maxLength = 2;//每项显示文字个数
                                    var valLength = value.length;//X轴类目项的文字个数
                                    var rowN = Math.ceil(valLength / maxLength); //类目项需要换行的行数
                                    if (rowN > 1)//如果类目项的文字大于3,
                                    {
                                        for (var i = 0; i < rowN; i++) {
                                            var temp = "";//每次截取的字符串
                                            var start = i * maxLength;//开始截取的位置
                                            var end = start + maxLength;//结束截取的位置
                                            //这里也可以加一个是否是最后一行的判断，但是不加也没有影响，那就不加吧
                                            temp = value.substring(start, end) + "\n";
                                            ret += temp; //凭借最终的字符串
                                        }
                                        return ret;
                                    }
                                    else {
                                        return value;
                                    }
                                }
                            }
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [
                            {
                                itemStyle: {
                                    normal: {
                                        color: '#a80001'
                                    }
                                },
                                type: 'bar',
                                data: results
                            }
                        ]
                    }
                )
            },
            error: function () {
                bar3Chart.hideLoading();
                bar3Chart.setOption(
                    {
                        backgroundColor: '#1A1A1A',
                        title: {
                            text: 'Load Fail'.split("").join("\n"),
                            textStyle: {
                                shadowColor: '#000000',
                                shadowBlur: 5,
                                shadowOffsetX: 3,
                                shadowOffsetY: 3,
                                color: '#e4e4e4',
                            },
                            top: '20%',
                            left: '2%'
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {type: 'shadow'}
                        },
                        grid: {
                            top: '10%',
                            left: '10%',
                            right: '10%',
                            bottom: '20%',
                            containLabel: true
                        },
                        textStyle: {
                            shadowColor: '#000000',
                            shadowBlur: 5,
                            shadowOffsetX: 3,
                            shadowOffsetY: 3,
                            color: '#e4e4e4',
                        },
                        xAxis: {
                            type: 'category',
                            data: [
                                'BC\n↓\nA', 'BD\n↓\nA', 'CD\n↓\nA',
                                'AC\n↓\nB', 'AD\n↓\nB', 'CD\n↓\nB',
                                'AB\n↓\nC', 'AD\n↓\nC', 'BD\n↓\nC',
                                'AB\n↓\nD', 'AC\n↓\nD', 'BC\n↓\nD'
                            ],
                            axisLabel: {
                                interval: 0,
                                formatter: function (value) {
                                    var ret = "";//拼接加\n返回的类目项
                                    var maxLength = 2;//每项显示文字个数
                                    var valLength = value.length;//X轴类目项的文字个数
                                    var rowN = Math.ceil(valLength / maxLength); //类目项需要换行的行数
                                    if (rowN > 1)//如果类目项的文字大于3,
                                    {
                                        for (var i = 0; i < rowN; i++) {
                                            var temp = "";//每次截取的字符串
                                            var start = i * maxLength;//开始截取的位置
                                            var end = start + maxLength;//结束截取的位置
                                            //这里也可以加一个是否是最后一行的判断，但是不加也没有影响，那就不加吧
                                            temp = value.substring(start, end) + "\n";
                                            ret += temp; //凭借最终的字符串
                                        }
                                        return ret;
                                    }
                                    else {
                                        return value;
                                    }
                                }
                            }
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [
                            {
                                itemStyle: {
                                    normal: {
                                        color: '#a80001'
                                    }
                                },
                                type: 'bar',
                                data: []
                            }
                        ]
                    }
                )
            }
        })
    });
    //bar4
    $(function () {
        bar4Chart.setOption(
            {
                backgroundColor: '#1A1A1A',
                title: {
                    text: '汽车保有量城市',
                    subtext: '2016年',
                    left: 'center',
                    top: 10,
                    textStyle: {
                        fontWeight: 400,
                        fontSize: 30,
                        shadowColor: '#000000',
                        shadowBlur: 5,
                        shadowOffsetX: 3,
                        shadowOffsetY: 3,
                        color: '#e4e4e4',
                    },
                },
                textStyle: {
                    shadowColor: '#000000',
                    shadowBlur: 5,
                    shadowOffsetX: 3,
                    shadowOffsetY: 3,
                    color: '#e4e4e4',
                },
                color: ['rgb(25, 183, 207)'],
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {type: 'shadow'}
                },
                grid: {
                    top: 80,
                    containLabel: true
                },
                xAxis: {
                    type: 'category',
                    data: [
                        '北京', '成都', '深圳', '上海', '重庆', '天津', '苏州', '郑州', '杭州', '广州', '西安'
                    ],
                },
                yAxis: {
                    type: 'value'
                },
                series: {
                    name: '数量',
                    type: 'bar',
                    data: [5350000, 3660000, 3150000, 2840000, 2790000, 2730000, 2690000, 2390000, 2240000, 2240000, 2190000],
                    label: {
                        normal: {
                            show: true,
                            position: 'insideTop',
                            formatter: function (params) {
                                return '' + params.data / 10000 + '万';
                            },
                            shadowBlur: 5,
                            shadowOffsetX: 3,
                            shadowOffsetY: 3,
                            textShadowBlur: 5,
                            textShadowOffsetX: 3,
                            textShadowOffsetY: 3
                        }
                    }
                }
            }
        )
    });
    //bar5
    $(function () {
        bar5Chart.setOption(
            {
                title: {
                    text: '部分省份年度交通事故数',
                    left: 'center',
                    top: 10,
                    textStyle: {
                        fontWeight: 400,
                        fontSize: 30,
                        shadowColor: '#000000',
                        shadowBlur: 5,
                        shadowOffsetX: 3,
                        shadowOffsetY: 3,
                        color: '#e4e4e4',
                    },
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {type: 'shadow'}
                },
                grid: {
                    top: 80,
                    containLabel: true
                },
                backgroundColor: '#1A1A1A',
                legend: {
                    top: '20%',
                    right: '15%',
                    textStyle: {
                        shadowColor: '#000000',
                        shadowBlur: 5,
                        shadowOffsetX: 3,
                        shadowOffsetY: 3,
                        color: '#e4e4e4',
                    },
                    data: ['2016', '2015', '2014']
                },
                textStyle: {
                    shadowColor: '#000000',
                    shadowBlur: 5,
                    shadowOffsetX: 3,
                    shadowOffsetY: 3,
                    color: '#e4e4e4',
                },
                xAxis: {
                    type: 'category',
                    data: ['广东', '湖北', '浙江', '江苏', '山东', '安徽', '贵州', '北京']
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        type: 'bar',
                        name: '2016',
                        data: [24773, 16908, 14791, 13299, 13163, 12933, 12579, 3163],
                        itemStyle: {
                            normal: {
                                color: '#77A8AD'
                            }
                        },
                        label: {
                            normal: {
                                show: true,
                                position: 'insideTop',
                                formatter: function (params) {
                                    return Math.floor(params.data / 100);
                                }
                            }
                        }
                    },
                    {
                        type: 'bar',
                        name: '2015',
                        data: [24676, 4624, 16270, 12999, 13376, 13770, 1035, 2637],
                        label: {
                            normal: {
                                show: true,
                                position: 'insideTop',
                                formatter: function (params) {
                                    return Math.floor(params.data / 100);
                                }
                            }
                        }
                    },
                    {
                        type: 'bar',
                        name: '2014',
                        data: [26445, 5271, 17135, 13187, 13570, 16077, 1145, 3196],
                        label: {
                            normal: {
                                show: true,
                                position: 'insideTop',
                                formatter: function (params) {
                                    return Math.floor(params.data / 100);
                                }
                            }
                        }
                    }
                ]
            }
        )
    });
    //pie
    $(function () {
        pieChart.hideLoading();
        pieChart.setOption({
            backgroundColor: '#1A1A1A',
            title: {
                text: '平均各类事故死亡人数',
                left: 'center',
                top: 10,
                textStyle: {
                    fontWeight: 400,
                    fontSize: 30,
                    shadowColor: '#000000',
                    shadowBlur: 5,
                    shadowOffsetX: 3,
                    shadowOffsetY: 3,
                    color: '#e4e4e4',
                },
            },
            tooltip: {
                trigger: 'item'
            },
            series: [
                {
                    type: 'pie',
                    radius: '60%',
                    center: ['50%', '55%'],
                    data: [
                        {name: '道路交通', value: 0.788},
                        {name: '铁路路外', value: 0.065},
                        {name: '水上交通', value: 0.003},
                        {name: '民航', value: 0.001},
                        {name: '农机', value: 0.024},
                        {name: '其他', value: 0.006},
                        {name: '工矿企业', value: 0.093},
                        {name: '消防火灾', value: 0.016},
                    ],
                    label: {
                        normal: {
                            textStyle: {
                                shadowColor: '#000000',
                                shadowBlur: 5,
                                shadowOffsetX: 3,
                                shadowOffsetY: 3,
                                color: '#e4e4e4',
                            },
                        }
                    },
                    labelLine: {
                        normal: {
                            lineStyle: {
                                color: 'rgba(255, 255, 255, 0.4)'
                            }
                        }
                    },
                    animationEasing: 'elasticOut',
                    animationType: 'scale',
                    startAngle: 330,
                    itemStyle: {
                        normal: {
                            shadowBlur: 200,
                            shadowOffsetX: 0,
                            shadowOffsetY: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        });
    });
    //pie2
    $(function () {
        pie2Chart.showLoading();
        $.ajax(
            {
                url: "/Urban_Road_Safety_Analysis/PersResult2Servlet",
                timeout: 5000,
                success: function (data) {
                    var pRes2s = JSON.parse(data);
                    var pRes2sScore = pRes2s.score;
                    var pRes2sResult = pRes2s.res;
                    $("#pA").text(pRes2sResult[0]);
                    $("#pD").text(pRes2sResult[1]);
                    $("#pC").text(pRes2sResult[2]);
                    pie2Chart.hideLoading();
                    pie2Chart.setOption(
                        {
                            baseOption: {
                                backgroundColor: '#1A1A1A',
                                title: [
                                    {
                                        text: '各因素受双因素的影响',
                                        left: 'center',
                                        top: 10,
                                        textStyle: {
                                            fontWeight: 400,
                                            fontSize: 30,
                                            shadowColor: '#000000',
                                            shadowBlur: 5,
                                            shadowOffsetX: 3,
                                            shadowOffsetY: 3,
                                            color: '#e4e4e4',
                                        },
                                    },
                                    {
                                        text: '道路风险感知能力',
                                    }, {
                                        text: '危险驾驶行为',
                                    }, {
                                        text: '驾驶能力自信',
                                    }
                                ],
                                series: [{
                                    name: 'A',
                                    type: 'pie',
                                    roseType: 'radius',
                                    label: {
                                        normal: {
                                            show: false
                                        },
                                        emphasis: {
                                            show: true,
                                            formatter: '{b}\n{c}',
                                            textBorderColor: '#101010',
                                            textBorderWidth: 1,
                                            textShadowColor: '#101010',
                                            textShadowBlur: 4,
                                            textShadowOffsetX: 2,
                                            textShadowOffsetY: 2
                                        }
                                    },
                                    lableLine: {
                                        normal: {
                                            show: false
                                        },
                                        emphasis: {
                                            show: true
                                        }
                                    },
                                    data: [
                                        {value: pRes2sScore[0], name: '危险驾驶行为&\n驾驶能力自信'},
                                        {value: pRes2sScore[1], name: '危险驾驶行为&\n人格特性'},
                                        {value: pRes2sScore[2], name: '驾驶能力自信&\n人格特性'}
                                    ]
                                }, {
                                    name: 'D',
                                    type: 'pie',
                                    roseType: 'radius',
                                    label: {
                                        normal: {
                                            show: false
                                        },
                                        emphasis: {
                                            show: true,
                                            formatter: '{b}\n{c}',
                                            textBorderColor: '#101010',
                                            textBorderWidth: 1,
                                            textShadowColor: '#101010',
                                            textShadowBlur: 4,
                                            textShadowOffsetX: 2,
                                            textShadowOffsetY: 2
                                        }
                                    },
                                    lableLine: {
                                        normal: {
                                            show: false
                                        },
                                        emphasis: {
                                            show: true
                                        }
                                    },
                                    data: [
                                        {value: pRes2sScore[3], name: '道路风险感知能力&\n驾驶能力自信'},
                                        {value: pRes2sScore[4], name: '道路风险感知能力&\n人格特性'},
                                        {value: pRes2sScore[5], name: '驾驶能力自信&\n人格特性'}
                                    ]
                                },
                                    {
                                        name: 'C',
                                        type: 'pie',
                                        roseType: 'radius',
                                        label: {
                                            normal: {
                                                show: false
                                            },
                                            emphasis: {
                                                show: true,
                                                formatter: '{b}\n{c}',
                                                textBorderColor: '#101010',
                                                textBorderWidth: 1,
                                                textShadowColor: '#101010',
                                                textShadowBlur: 4,
                                                textShadowOffsetX: 2,
                                                textShadowOffsetY: 2
                                            }
                                        },
                                        lableLine: {
                                            normal: {
                                                show: false
                                            },
                                            emphasis: {
                                                show: true
                                            }
                                        },
                                        data: [
                                            {value: pRes2sScore[6], name: '道路风险感知能力&\n危险驾驶行为'},
                                            {value: pRes2sScore[7], name: '道路风险感知能力&\n人格特性'},
                                            {value: pRes2sScore[8], name: '危险驾驶行为&\n人格特性'}
                                        ]
                                    }
                                ],
                            },
                            media: [
                                {
                                    query: {
                                        minWidth: 815,
                                        maxWidth: 1450
                                    },
                                    option: {
                                        title: [
                                            {},
                                            {
                                                left: '18%',
                                                top: '20%',
                                                textStyle: {
                                                    fontWeight: 400,
                                                    fontSize: 20,
                                                    shadowColor: '#000000',
                                                    shadowBlur: 5,
                                                    shadowOffsetX: 3,
                                                    shadowOffsetY: 3,
                                                    color: '#e4e4e4',
                                                },
                                            },
                                            {
                                                left: '44%',
                                                top: '20%',
                                                textStyle: {
                                                    fontWeight: 400,
                                                    fontSize: 20,
                                                    shadowColor: '#000000',
                                                    shadowBlur: 5,
                                                    shadowOffsetX: 3,
                                                    shadowOffsetY: 3,
                                                    color: '#e4e4e4',
                                                },
                                            },
                                            {
                                                left: '69%',
                                                top: '20%',
                                                textStyle: {
                                                    fontWeight: 400,
                                                    fontSize: 20,
                                                    shadowColor: '#000000',
                                                    shadowBlur: 5,
                                                    shadowOffsetX: 3,
                                                    shadowOffsetY: 3,
                                                    color: '#e4e4e4',
                                                },
                                            }
                                        ],
                                        series: [
                                            {
                                                radius: [20, 110],
                                                center: ['25%', '50%'],
                                            },
                                            {
                                                radius: [20, 110],
                                                center: ['50%', '50%']
                                            },
                                            {
                                                radius: [20, 110],
                                                center: ['75%', '50%'],
                                            }
                                        ]
                                    }
                                },
                                {
                                    query: {
                                        minWidth: 614,
                                        maxWidth: 815
                                    },
                                    option: {
                                        title: [
                                            {},
                                            {
                                                left: '25%',
                                                top: '15%',
                                                textStyle: {
                                                    fontWeight: 400,
                                                    fontSize: 15,
                                                    shadowColor: '#000000',
                                                    shadowBlur: 5,
                                                    shadowOffsetX: 3,
                                                    shadowOffsetY: 3,
                                                    color: '#e4e4e4',
                                                }
                                            },
                                            {
                                                left: '60%',
                                                top: '15%',
                                                textStyle: {
                                                    fontWeight: 400,
                                                    fontSize: 15,
                                                    shadowColor: '#000000',
                                                    shadowBlur: 5,
                                                    shadowOffsetX: 3,
                                                    shadowOffsetY: 3,
                                                    color: '#e4e4e4',
                                                }
                                            },
                                            {
                                                left: '62%',
                                                top: '77%',
                                                textStyle: {
                                                    fontWeight: 400,
                                                    fontSize: 15,
                                                    shadowColor: '#000000',
                                                    shadowBlur: 5,
                                                    shadowOffsetX: 3,
                                                    shadowOffsetY: 3,
                                                    color: '#e4e4e4',
                                                }
                                            }
                                        ],
                                        series: [
                                            {
                                                radius: [15, 90],
                                                center: ['33%', '40%'],
                                            },
                                            {
                                                radius: [15, 90],
                                                center: ['66%', '40%']
                                            },
                                            {
                                                radius: [15, 90],
                                                center: ['48%', '80%'],
                                            }
                                        ]
                                    }
                                },
                                {
                                    query: {
                                        minWidth: 320,
                                        maxWidth: 614
                                    },
                                    option: {
                                        title: [
                                            {},
                                            {
                                                left: '19%',
                                                top: '15%',
                                                textStyle: {
                                                    fontWeight: 400,
                                                    fontSize: 15,
                                                    shadowColor: '#000000',
                                                    shadowBlur: 5,
                                                    shadowOffsetX: 3,
                                                    shadowOffsetY: 3,
                                                    color: '#e4e4e4',
                                                }
                                            },
                                            {
                                                left: '56%',
                                                top: '15%',
                                                textStyle: {
                                                    fontWeight: 400,
                                                    fontSize: 15,
                                                    shadowColor: '#000000',
                                                    shadowBlur: 5,
                                                    shadowOffsetX: 3,
                                                    shadowOffsetY: 3,
                                                    color: '#e4e4e4',
                                                }
                                            },
                                            {
                                                left: '62%',
                                                top: '77%',
                                                textStyle: {
                                                    fontWeight: 400,
                                                    fontSize: 15,
                                                    shadowColor: '#000000',
                                                    shadowBlur: 5,
                                                    shadowOffsetX: 3,
                                                    shadowOffsetY: 3,
                                                    color: '#e4e4e4',
                                                }
                                            }
                                        ],
                                        series: [
                                            {
                                                radius: [7, 60],
                                                center: ['33%', '40%'],
                                            },
                                            {
                                                radius: [7, 60],
                                                center: ['66%', '40%']
                                            },
                                            {
                                                radius: [7, 60],
                                                center: ['48%', '80%'],
                                            }
                                        ]
                                    }
                                },
                                {
                                    option: {
                                        title: [
                                            {},
                                            {
                                                left: '18%',
                                                top: '20%',
                                                textStyle: {
                                                    fontWeight: 400,
                                                    fontSize: 20,
                                                    shadowColor: '#000000',
                                                    shadowBlur: 5,
                                                    shadowOffsetX: 3,
                                                    shadowOffsetY: 3,
                                                    color: '#e4e4e4',
                                                },
                                            },
                                            {
                                                left: '44%',
                                                top: '20%',
                                                textStyle: {
                                                    fontWeight: 400,
                                                    fontSize: 20,
                                                    shadowColor: '#000000',
                                                    shadowBlur: 5,
                                                    shadowOffsetX: 3,
                                                    shadowOffsetY: 3,
                                                    color: '#e4e4e4',
                                                },
                                            },
                                            {
                                                left: '69%',
                                                top: '20%',
                                                textStyle: {
                                                    fontWeight: 400,
                                                    fontSize: 20,
                                                    shadowColor: '#000000',
                                                    shadowBlur: 5,
                                                    shadowOffsetX: 3,
                                                    shadowOffsetY: 3,
                                                    color: '#e4e4e4',
                                                },
                                            }
                                        ],
                                        series: [
                                            {
                                                radius: [20, 110],
                                                center: ['25%', '50%'],
                                            },
                                            {
                                                radius: [20, 110],
                                                center: ['50%', '50%']
                                            },
                                            {
                                                radius: [20, 110],
                                                center: ['75%', '50%'],
                                            }
                                        ]
                                    }
                                }
                            ]
                        }
                    )
                },
                error: function () {
                    location.reload(true);
                }
            }
        )
    })

    /**
     * 分辨率改变 视图重绘
     * */

    function resize() {
        bar1Chart.resize();
        bar2Chart.resize();
        bar3Chart.resize();
        bar4Chart.resize();
        bar5Chart.resize();
        pieChart.resize();
        pie2Chart.resize();
    }

    window.onresize = function () {
        resize();
    }

    /**
     * 容器切换
     * */
    function display() {
        $(".quest-panel").fadeOut();
        $(".analysis-content").fadeOut();
        $(".persAnalysis-content").fadeOut();
        $(".quest").attr("class", "quest");
        $(".analysis").attr("class", "analysis");
        $(".persAnalysis").attr("class", "persAnalysis");
        $(".display-content").fadeIn();
        $(".display").attr("class", "active display");
        resize();
    };

    function quest() {
        $(".display-content").fadeOut();
        $(".analysis-content").fadeOut();
        $(".persAnalysis-content").fadeOut();
        $(".display").attr("class", "display");
        $(".analysis").attr("class", "analysis");
        $(".persAnalysis").attr("class", "persAnalysis");
        $(".quest-panel").fadeIn();
        $(".quest").attr("class", "active quest");
        resize();
    };

    function analysis() {
        $(".display-content").fadeOut();
        $(".quest-panel").fadeOut();
        $(".persAnalysis-content").fadeOut();
        $(".display").attr("class", "display");
        $(".quest").attr("class", "quest");
        $(".persAnalysis").attr("class", "persAnalysis");
        $(".analysis-content").fadeIn();
        $(".analysis").attr("class", "active analysis");
        resize();
    }

    function persAnalysis() {
        $(".display-content").fadeOut();
        $(".quest-panel").fadeOut();
        $(".analysis-content").fadeOut();
        $(".display").attr("class", "display");
        $(".quest").attr("class", "quest");
        $(".analysis").attr("class", "analysis");
        $(".persAnalysis-content").fadeIn();
        $(".persAnalysis").attr("class", "active persAnalysis");
        resize();
    }

    /**
     * 媒体导航栏适应
     * */
    function toggleBar() {
        if (screen.width < 768) {
            $(".navbar-toggle").click();
        }
    };

    /**
     * 问卷选项函数
     * questionnaire variable
     * */
    var questions;
    var questNum = 0;
    var options = new Array();
    var result;
    $(function () {
        $.getJSON('js/questions.json', function (data) {
            questions = data.questions;
            $(".quest-text").text((questNum + 1) + ". " + questions[questNum]);
        })
    });
    $("#quest-is").click(function quest_is() {
        if (questions[questNum + 1] != null) {
            options[questNum] = true;
            questNum++;
            $(".quest-text").text((questNum + 1) + ". " + questions[questNum]);
        } else {
            options[questNum] = true;
            $(".question").empty().html("<h3 class='text-center'>已完成，谢谢</h3>");
            result = {"options": options, "userId": '<%=user.getUserID()%>'};
            $.ajax({
                url: "/Urban_Road_Safety_Analysis/QuestServlet",
                data: result,
                success: function () {
                    alert("上传成功！");
                    location.replace("index?id=<%=user.getUserID()%>");
                },
                error: function () {
                    alert("上传失败！");
                }
            });
        }
    });
    $("#quest-not").click(function quest_not() {
        if (questions[questNum + 1] != null) {
            options[questNum] = false;
            questNum++;
            $(".quest-text").text((questNum + 1) + ". " + questions[questNum]);
        } else {
            options[questNum] = false;
            $(".question").empty().html("<h3 class='text-center'>已完成，谢谢</h3>");
            result = {"options": options, "userId": '<%=user.getUserID()%>'};
            $.ajax({
                url: "/Urban_Road_Safety_Analysis/QuestServlet",
                data: result,
                success: function () {
                    alert("上传成功！");
                    location.replace("index?id=<%=user.getUserID()%>");
                },
                error: function () {
                    alert("上传失败！");
                }
            });
        }
    });
</script>
</body>
</html>