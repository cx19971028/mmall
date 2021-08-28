$(function(){
    //给type绑定点击事件
    $(".type").click(function () {
        var index = $(".type").index(this);
        var obj = $(".type").eq(index);
        $(".type").removeClass("checked");
        obj.addClass("checked");
    });
    //给color绑定点击事件
    $(".color").click(function () {
        var index = $(".color").index(this);
        var obj = $(".color").eq(index);
        $(".color").removeClass("checked");
        obj.addClass("checked");
    });
});

//商品数量++
function increase() {
    var value = $("#quantity").val();
    var stock = $("#stock").text();
    value++;
    if(value > stock){
        value = stock;
    }
    $("#quantity").val(value);
}

//商品数量--
function reduce() {
    var value = $("#quantity").val();
    value--;
    if(value <= 0){
        value = 1;
    }
    $("#quantity").val(value);
}

//添加购物车
function addCart(){
    var productId = $("#productId").val();
    var price = $("#productPrice").val();
    var quantity = $("#quantity").val();
    var stock = $("#stock").text();
    if((stock-quantity)<0){
        alert("商品库存不足，请重新选择！");
        return;
    }else {
        window.location.href="/cart/cartList/"+productId+"/"+price+"/"+quantity;
    }
}