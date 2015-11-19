<html>
<head>
    <script src="js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript"></script>
    <script language="javascript" type="text/javascript">
        $(function(){

            wx.config({
                debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                appId: 'wxbc0d6ff65d0f13b3', // 必填，公众号的唯一标识
                timestamp: new Date().getTime() , // 必填，生成签名的时间戳
                nonceStr: 'ddddd', // 必填，生成签名的随机串
                signature: 'dddd',// 必填，签名，见附录1
                jsApiList: [] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
            });
        })
    </script>
</head>
<body>
<h2>Hello World!This is a Simple Page</h2>
</body>
</html>
