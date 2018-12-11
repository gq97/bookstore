
$(document).ready(function(){
//获得文本框对象
    var t = $("#book_amount");
    var max_t = $("#storage").val();
    //数量增加操作
    $("#add").click(function(){
        if(parseInt(t.val()) < max_t) {
            t.val(Math.abs(parseInt(t.val()))+1);
        }
    })
    //数量减少操作
    $("#min").click(function(){
        if (parseInt(t.val()) > 1){
            t.val(Math.abs(parseInt(t.val()))-1);
        }
    })
});