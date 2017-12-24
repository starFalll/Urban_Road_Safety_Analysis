<%@ page import="team.web_first.javabean.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    User user;
    user = (User) request.getSession().getAttribute("user");
    if (user == null) {
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        String newLocn = "/Urban_Road_Safety_Analysis/login.html";
        response.setHeader("Location", newLocn);
        return;
    }

%>

<html>
<head>
    <title>Home</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="shortcut icon" href="images/title.png">
    <link rel="icon" href="images/title.png">
    <link rel="Bookmark" href="images/title.png">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
          rel='stylesheet' type='text/css'>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdn.bootcss.com/echarts/3.8.5/echarts.simple.js"></script>

    <script>
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>

    <!--Close script-->
    <script>$(document).ready(function () {
        $('.alert-close').on('click', function () {
            $(this.parentNode).fadeOut();
        });
    });
    </script>


    <script>
        // 绘制图表
        $(document).ready(function start() {
                echarts.init(document.getElementById('pie')).setOption({
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
            }
        )
    </script>

    <!----Calender -------->
    <link rel="stylesheet" href="css/clndr.css" type="text/css"/>
    <!----End Calender -------->

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
                                <li><a href="/web_first/LogoutServlet"><span class="glyphicon glyphicon-log-out"></span>Log
                                    out</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
    <div class="display-content">
        <div class="pie-content">
            <div class="pie" id="pie"></div>
            <div class="alert-close"></div>
        </div>
        <div class="calenders">
            <div class="calender-left">
                <div class="alert-close"></div>
                <h2>Calendar</h2>
                <form>
                    <input type="text" class="text" value="Username" onFocus="this.value = '';"
                           onBlur="if (this.value == '') {this.value = 'Username';}">
                    <input type="text" class="text" value="Subject" onFocus="this.value = '';"
                           onBlur="if (this.value == '') {this.value = 'Subject';}">
                    <textarea value="" onFocus="this.value = '';"
                              onBlur="if (this.value == '') {this.value = 'Message';}">Message</textarea>
                    <input type="submit" value="SEND"/>
                </form>
            </div>
            <div class="clearfix"></div>
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

    var questions;
    var questNum = 0;
    var options = new Array();
    var result;
    $(document).ready(function () {
        $.getJSON("js/questions.json", function (data) {
            questions = data.questions;
            $(".quest-text").text((questNum + 1) + ". " + questions[questNum]);
        })
    })
    $("#quest-is").click(function () {
        if (questions[questNum] != null) {
            $(".quest-text").text((questNum + 1) + ". " + questions[questNum]);
            options[questNum] = true;
            questNum++;
        } else {
            $(".question").empty().html("<h3 class='text-center'>已完成，谢谢</h3>");
            result = {"options": options};
            $.ajax({
                url: "/web_first/QuestServlet",
                data: result,
                success: function () {
                    alert("上传成功！");
                },
                error: function () {
                    alert("上传失败！");
                }
            });
        }
    })
    $("#quest-not").click(function () {
        if (questions[questNum] != null) {
            $(".quest-text").text((questNum + 1) + ". " + questions[questNum]);
            options[questNum] = false;
            questNum++;
        } else {
            $(".question").empty().html("<h3 class='text-center'>已完成，谢谢</h3>");
            result = {"options": options};
            $.ajax({
                url: "/web_first/QuestServlet",
                data: result,
                success: function () {
                    alert("上传成功！");
                },
                error: function () {
                    alert("上传失败！");
                }
            });
        }
    })

    $(".display").click(function () {
        if (screen.width < 768) {
            $(".navbar-toggle").click();
        }
    })
    $(".quest").click(function () {
        if (screen.width < 768) {
            $(".navbar-toggle").click();
        }
    })
</script>
</body>
</html>