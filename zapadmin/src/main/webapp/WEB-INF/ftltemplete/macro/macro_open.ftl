 <#macro m_open_header>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="sitenav">
<meta name="author" content="srnpr">
<title>仓颉开放平台</title>
<link href="../resources/lib/bootthree/css/bootstrap.min.css"
	rel="stylesheet">

<!--[if lt IE 9]><script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script><![endif]-->

<link rel="shortcut icon" href="../resources/zapadmin/themes/history/icons/apiopen.ico" />
<link
	href="../resources/zapweb/css/w.css"
	rel="stylesheet">
	<link
	href="../resources/lib/jplugins/grumble/css/grumble.min.css"
	rel="stylesheet">

<style>
/*
 * Style tweaks
 * --------------------------------------------------
 */
html,body {
	overflow-x: hidden; /* Prevent scroll on narrow devices */
}

body {
	
}

footer {
	padding: 30px 0;
}

/*
 * Off Canvas
 * --------------------------------------------------
 */
@media screen and (max-width: 767px) {
	.row-offcanvas {
		position: relative;
		-webkit-transition: all 0.25s ease-out;
		-moz-transition: all 0.25s ease-out;
		transition: all 0.25s ease-out;
	}
	.row-offcanvas-right
  .sidebar-offcanvas {
		right: -50%; /* 6 columns */
	}
	.row-offcanvas-left
  .sidebar-offcanvas {
		left: -50%; /* 6 columns */
	}
	.row-offcanvas-right.active {
		right: 50%; /* 6 columns */
	}
	.row-offcanvas-left.active {
		left: 50%; /* 6 columns */
	}
	.sidebar-offcanvas {
		position: absolute;
		top: 0;
		width: 50%; /* 6 columns */
	}
}
</style>



<style>
body {
	font-family: "Microsoft Yahei", Verdana, Simsun, "Segoe UI Light",
		"Segoe UI Web Light", "Segoe UI Web Regular", "Segoe UI",
		"Segoe UI Symbol", "HelveticaNeue-Light", "Helvetica Neue", Arial;
	word-wrap: break-word;
}

.navbar-inverse {
	background-color: #1fa67a;
	border-color: #1fa67a;
	border-radius:0px;
	border:0px;
	
	border-top: 1px solid #34a782;
	border-bottom: 1px solid #1c9971;
}

.navbar-inverse .navbar-brand {
	color: #fff;
}

.navbar-inverse .navbar-nav>li>a {
	color: #fff;
}

.index_listbox .list-group-item a {
	padding-left: 12px;
}

.navbar-inverse .navbar-nav>.active>a,.navbar-inverse .navbar-nav>.active>a:hover,.navbar-inverse .navbar-nav>.active>a:focus
	{
	background-color: #1c9971;
}
</style>


<script>
function func_open_rumble(s)
{
	$('#'+s+' span').grumble(
	{
		text: '结构描述', 
		angle: 240, 
		distance: 0, 
		showAfter: 300,
		//type: 'alt-', 
		hideAfter: 3000
	}
);
}
</script>

</head>
<body>
	</#macro> <#macro m_open_footer>
	<script src="../resources/lib/jquery/jquery-last.min.js"></script>
	<script src="../resources/lib/bootthree/js/bootstrap.min.js"></script>
	<script src="../resources/lib/jplugins/grumble/js/jquery.grumble.min.js"></script>
</body>
</html>
</#macro> <#macro m_open_show_class p_class_model p_show_type=0>

<table class="table  table-condensed table-bordered table-hover">
	<tr class="active">
		<th style="width: 10%;">参数名称</th>
		<th style="width: 10%;">类型</th> <#if (p_show_type==0)>
		<th style="width: 10%;">是否必须</th> </#if>
		<th style="width: 20%;">参数描述</th>
		<th>备注</th>
	</tr>


	<#local input_field=p_class_model.getFields()> <#list input_field?keys
	as testKey>
	<tr>
		<td>${testKey}</td>
		<td><#if (input_field[testKey].getFieldClass()!="")> <a
			href="#apiinfo_field_${input_field[testKey].getFieldClass()?replace('.','_')}" onclick="func_open_rumble('apiinfo_field_${input_field[testKey].getFieldClass()?replace('.','_')}')">
				${input_field[testKey].getType()} </a> <#else>

			${input_field[testKey].getType()} </#if>


		</td> <#if (p_show_type==0)>
		<td>${input_field[testKey].getFlagRequire()}</td> </#if>
		<td>${input_field[testKey].getTitle()}</td>
		<td>${input_field[testKey].getRemark()}</td>
	</tr>
	</#list>
</table>

</#macro> <#macro m_open_format_name p_class_name>
${p_class_name?replace(".","_")} </#macro> <#macro m_open_format_type
p_type_code> <#if p_type_code=="467701200001">
<span class="label label-danger"><span
	class="glyphicon glyphicon-star"></span>&nbsp;私有&nbsp;&nbsp;</span>

<#else>
<span class="label label-success"><span
	class="glyphicon glyphicon-globe"></span>&nbsp;公开&nbsp;&nbsp;</span>
</#if> </#macro>










