$(function(){
    compute_total();
});
// 计算所有商品的总计
function compute_total(){
    //计算总价
    var array = $(".qprice");
    var totalCost = 0;
    for(var i = 0;i < array.length;i++){
        var val = parseInt($(".qprice").eq(i).html().substring(1));
        totalCost += val;
    }
    $("#totalprice").html("￥"+totalCost);
    //settlement2使用
    $("#settlement2_totalCost").val(totalCost);
}

//商品数量++
function addQuantity(cartId) {
    let quantity = parseInt($("#"+cartId).val());
    let stock = parseInt($("#productStock"+cartId).val());
    if(quantity >= stock){
        alert("库存不足！");
        return;
    }
    quantity++
    $("#"+cartId).val(quantity);
    compute_price(quantity, cartId);
    compute_total();

}
// 计算每个商品的总价
function compute_price(quantity, cartId){
    let price = parseFloat($("#price"+cartId).text());
    let cost = price*quantity;
    // alert(cost)
    $.ajax({
        url:"/cart/updateCart/"+cartId+"/"+quantity+"/"+cost,
        type:"post",
        success:function (data){
            if(data.success){
                $("#qprice"+cartId).text("￥"+(cost));
            }
        }
    })
}

//商品数量--
function subQuantity(cartId) {
    let quantity = parseInt($("#"+cartId).val());
    if(quantity == 1){
        alert("至少选择一件商品！");
        return;
    }
    quantity--;
    compute_price(quantity, cartId);
    $("#"+cartId).val(quantity);
    compute_total();
}

//移出购物车
function removeCart(cartId){
    if(confirm("是否确定要删除？")){
        window.location.href = "/cart/removeCart/"+cartId;
    }
}