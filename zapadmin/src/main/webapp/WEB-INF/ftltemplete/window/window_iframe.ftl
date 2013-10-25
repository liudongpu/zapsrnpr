<@m_common_page_head_common e_title=b_page.getWebPage().getPageName() e_bodyclass="zab_page_default_body" />

<div id="window_iframe_box" class="window_iframe_box  w_clear">

<@m_zapmacro_common_ajax_chart b_page/>

</div>
<div class="w_p_20">
<input type="button" class="btn  window_iframe_btn" value="确认选择"/>
</div>


<script type="text/javascript">
zapjs.f.require(["zapadmin/js/zapadmin_chartajax"],function(a){zapadmin_chartajax.init({pagecode:'page_chart_v_seller_pc_skuinfo',id:'window_iframe_box'});});
</script>

<@m_common_page_foot_base  />
