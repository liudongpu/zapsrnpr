<@m_common_page_head_common e_title=b_page.getWebPage().getPageName() e_bodyclass="zab_page_default_body" />

<#assign parentid=b_page.upReqValue("zw_s_iframe_select_source") >

<#assign boxid=parentid >

<div id="${boxid}" class="window_iframe_box  w_clear">

<@m_zapmacro_common_ajax_chart b_page/>

</div>
<div class="w_p_20">
<input type="button" class="btn  window_iframe_btn" value="确认选择" onclick="zapadmin_chartajax.ok_value('${b_page.upReqValue("zw_s_iframe_select_callback")?default("")}','${parentid}','${boxid}')"/>
</div>


<script type="text/javascript">
zapjs.f.require(["zapadmin/js/zapadmin_chartajax"],function(a){zapadmin_chartajax.init({pagecode:'${b_page.upReqValue("zw_s_iframe_select_page")}',id:'${boxid}',maxnum:'${b_page.upReqValue("zw_s_iframe_max_select")?default("0")}'});});
</script>

<@m_common_page_foot_base  />
