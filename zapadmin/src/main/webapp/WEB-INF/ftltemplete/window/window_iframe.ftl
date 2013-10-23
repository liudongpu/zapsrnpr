<@m_common_page_head_common e_title=b_page.getWebPage().getPageName() e_bodyclass="zab_page_default_body" />

<div class="window_iframe_box w_display">

<@m_zapmacro_common_page_chart b_page />

</div>
<div class="w_p_20">
<input type="button" class="btn  window_iframe_btn" value="确认选择"/>
</div>


<script type="text/javascript">
zapjs.f.require(["zapadmin/js/zapadmin_iframe_select"],function(a){a.init();});
</script>

<@m_common_page_foot_base  />
