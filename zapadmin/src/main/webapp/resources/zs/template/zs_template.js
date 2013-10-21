var zs_template = {
	temp : {},

	init : function() {

		$('.zat_dragable_item').draggable({
			revert : true,
			proxy : 'clone',
			handle:'.zat_dragable_priview'
		}).droppable({
			onDragOver : function(e, source) {
				/*
				indicator.css({
				display : 'block',
				left : $(this).offset().left - 10,
				top : $(this).offset().top + $(this).outerHeight() - 5
				});*/
				//$(source).addClass('zat_dragable_border');
				$(this).addClass('zat_dragable_border');
			},
			onDragLeave : function(e, source) {
				$(this).removeClass('zat_dragable_border');
			},
			onDrop : function(e, source) {
				$(this).removeClass('zat_dragable_border');
				
				
				if($(source).offset().top>$(this).offset().top)
				{
					$(source).insertBefore(this);
				}
				else
				{
					$(source).insertAfter(this);
				}
				
				

			}
		});
		
		
		
		$('.zat_dragable_control').draggable({
			
			
		});
		
		

		/*
		 $('.zat_main_right .zat_target_box').droppable({
		 accept : '.zat_dragable',
		 onDragEnter : function(e, source) {
		 //$(source).find('.zat_dragable_view').show();
		 },
		 onDragLeave : function(e, source) {

		 },
		 onDrop : function(e, source) {

		 if ($(source).hasClass('assigned')) {
		 $(this).append(source);

		 } else {
		 var c = $(source).clone().addClass('zat_drag_item');
		 $(this).append(c);
		 c.removeClass('zat_dragable');
		 c.draggable({
		 revert : true,

		 onBeforeDrag:function(e, source)
		 {
		 $('.zat_main_right .zat_target_box').droppable({disabled:true});
		 },

		 onDrop : function(e, source) {
		 $(source).insertAfter(this);

		 //indicator.hide();
		 }
		 });
		 }
		 }
		 });
		 */
	}
};

zs.f.define("zs/template/zs_template", [""], zs_template);

