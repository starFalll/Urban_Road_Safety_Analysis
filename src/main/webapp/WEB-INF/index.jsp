<%@ page import="team.web_first.javabean.User" %>
<%@ page import="org.json.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    User user;
    user = (User) request.getSession().getAttribute("user");
%>

<html>
<head>
    <title>Home</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="shortcut icon" href="../images/title.png">
    <link rel="icon" href="../images/title.png">
    <link rel="Bookmark" href="../images/title.png">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
          rel='stylesheet' type='text/css'>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcss.com/echarts/3.8.5/echarts.min.js"></script>
    <script>
        $(window).on('load', function () {
            window.scrollTo(0, 1);
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
                        <li class="active display"><a href="javascript:void(0)" onclick="display()">Display</a></li>
                        <li class="quest"><a href="javascript:void(0)" onclick="quest()">Questionnaire</a></li>
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
        <div class="bar-content">
            <div class="bar" id="bar2"></div>
        </div>
        <div class="bar-content">
            <div class="bar" id="bar1"></div>
        </div>
        <div class="pie-content">
            <div class="pie" id="pie"></div>
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

    /**
     * 绘制图表
     * */
    var bar1Chart = echarts.init(document.getElementById('bar1'));
    var bar2Chart = echarts.init(document.getElementById('bar2'));
    var pieChart = echarts.init(document.getElementById('pie'));
    //bar1
    $(function () {
        bar1Chart.showLoading();
        $.ajax(
            {
                url: "/Urban_Road_Safety_Analysis/PersResultServlet",
                timeout: 5000,
                data: <%=new JSONObject(user)%>,
                success: function (data) {
                    var persResult = JSON.parse(data);
                    bar1Chart.hideLoading();
                    bar1Chart.setOption({
                        title: {
                            text: '个人测试结果'.split("").join("\n"),
                            textStyle: {
                                color: 'rgba(255, 255, 255, 0.3)',
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
                            color: 'rgba(255, 255, 255, 0.3)'
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
                        title: {
                            text: '请先完成个人测试',
                            textStyle: {
                                color: 'rgba(255, 255, 255, 0.3)',
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
                            color: 'rgba(255, 255, 255, 0.3)'
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
        pieChart.showLoading();
        $.ajax({
            url: "/Urban_Road_Safety_Analysis/ResultServlet",
            success: function (data) {
                results = JSON.parse(data);
                for (i = 0; i < 4; i++) {
                    for (j = 0; j < 4; j++) {
                        if (i != j) {
                            for (k = 0, len = results.length; k < len; k++) {
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
                        title: {
                            text: '两因素相关系数'.split("").join("\n"),
                            textStyle: {
                                color: 'rgba(255, 255, 255, 0.3)',

                            },
                            top: '35%',
                            left: '2%'
                        },
                        textStyle: {
                            color: 'rgba(255, 255, 255, 0.3)'
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {type: 'shadow'}
                        },
                        legend: {
                            top: '3%',
                            textStyle: {
                                color: 'rgba(255, 255, 255, 0.3)'
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
                        title: {
                            text: 'Load Fail',
                            textStyle: {
                                color: 'rgba(255, 255, 255, 0.3)',

                            },
                            top: '2%',
                            left: '43%',
                        },
                        textStyle: {
                            color: 'rgba(255, 255, 255, 0.3)'
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
    //pie
    $(function () {
        pieChart.hideLoading();
        pieChart.setOption({
            backgroundColor: '#2c343c',
            visualMap: {
                // 不显示 visualMap 组件，只用于明暗度的映射
                show: false,
                min: 1000,
                max: 2500,
                inRange: {
                    colorLightness: [0, 1]
                }
            },
            series: [
                {
                    type: 'pie',
                    roseType: 'radius',
                    radius: '70%',
                    center: ['50%', '50%'],
                    data: [
                        {name: 'A', value: 1212},
                        {name: 'B', value: 2323},
                        {name: 'C', value: 1919}
                    ],
                    label: {
                        normal: {
                            textStyle: {
                                color: 'rgba(255, 255, 255, 0.3)'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            lineStyle: {
                                color: 'rgba(255, 255, 255, 0.3)'
                            }
                        }
                    },
                    animationEasing: 'elasticOut',
                    animationType: 'scale',
                    itemStyle: {
                        normal: {
                            shadowBlur: 200,
                            shadowOffsetX: 0,
                            shadowOffsetY: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)',
                            color: '#c23531',
                            shadowBlur: 200,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        });
    });

    /**
     * 分辨率改变 视图重绘
     * */
    window.onresize = function () {
        bar1Chart.resize();
        bar2Chart.resize();
        pieChart.resize();
    }

    /**
     * 容器切换
     * */
    function display() {
        $(".quest-panel").fadeOut();
        $(".quest").attr("class", "quest");
        $(".display-content").fadeIn();
        $(".display").attr("class", "active display");
    };

    function quest() {
        $(".display-content").fadeOut();
        $(".display").attr("class", "display");
        $(".quest-panel").fadeIn();
        $(".quest").attr("class", "active quest");
    };

    /**
     * 媒体导航栏适应
     * */
    function toggleBar() {
        if (screen.width < 768) {
            $(".navbar-toggle").click();
        }
    };
    $(".display").click(toggleBar());
    $(".quest").click(toggleBar());

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