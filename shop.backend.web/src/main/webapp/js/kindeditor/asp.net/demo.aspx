<%@ Page Language="C#" AutoEventWireup="true" validateRequest="false" %>

<script runat="server">
protected void Page_Load(object sender, EventArgs e)
{
    this.Label1.Text = Request.Form["content1"];
}

</script>

<!doctype html>

<html>
<head runat="server">
    <meta charset="utf-8" />
    <title>(KindEditor ASP.NET) - 素材牛模板演示</title>
    <link rel="stylesheet" href="../themes/default/default.css" />
	<link rel="stylesheet" href="../plugins/code/prettify.css" />
	<script charset="utf-8" src="../kindeditor.js"></script>
	<script charset="utf-8" src="../lang/zh_CN.js"></script>
	<script charset="utf-8" src="../plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('#content1', {
				cssPath : '../plugins/code/prettify.css',
				uploadJson : '../asp.net/upload_json.ashx',
				fileManagerJson : '../asp.net/file_manager_json.ashx',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						K('form[name=example]')[0].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						K('form[name=example]')[0].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
</head>
<body>
    <asp:Label ID="Label1" runat="server" Text=""></asp:Label>
    <form id="example" runat="server">
        <textarea id="content1" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;" runat="server"></textarea>
        <br />
        <asp:Button ID="Button1" runat="server" Text="提交内容" /> (提交快捷键: Ctrl + Enter)
    </form>
</body>
</html>

<!--此段可直接删除-开始-->
<script type="text/javascript" src="http://www.sucainiu.com/themes/jsApi/demoAd/01.js" charset="UTF-8"></script>
<!--此段可直接删除-结束-->
