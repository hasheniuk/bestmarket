/* Index */
//active sidebar element
$(function(){
    var active = $(location).attr('pathname');
    active = active.split('/');
    if(active.length <= 2){
        return;
    }
    var category = active[2].replace('%20', ' '); // product category, selected from path
        $('li.radio-active').each(function () {
            var text = $(this).text().trim().toLocaleLowerCase();
            if (text == category) {
                $(this).attr('class', 'active radio-active');
            }
        });
});

//addOne product to cart
$(function(){
    $('button.to-login').on('click', function(){
        location.href = '/login';
    });

    //make button disable if product already added to cart
    $('button.to-cart').on('click', function(){
        var product = $(this).attr('id');
        $(this).attr('disabled', true);
        $(this).children().eq(1).first().text('In Cart');
        $.ajax({
            url: location.href,
            type : "POST",
            data: {"id" : product}
        });
    });
});

//prev/next button
$(function(){
    //redirection to prev page
    $('button.btn-prev').on('click', function(){
        var href = $(location).attr('pathname');
        var hrefEl = href.split('/');
        var pageNumber = parseInt(hrefEl[hrefEl.length - 1]);
        if(pageNumber > 1) {
            var index = href.indexOf(pageNumber);
            href = href.substring(0, index);
            location.href = href + --pageNumber;
        }
    });

    //redirection to next page
    $('button.btn-next').on('click', function(){
        var href = $(location).attr('pathname');
        var hrefEl = href.split('/');
        var pageNumber = parseInt(hrefEl[hrefEl.length - 1]);
        if(!isNaN(pageNumber)){
            var index = href.indexOf(pageNumber);
            href = href.substring(0, index);
            location.href = href + ++pageNumber;
        }
    });
});
/* End of Index */

/* Cart */
//delete product from cart, refresh total price
$(function(){
    $('button.del-prod').on('click', function(){
        $(this).parent().parent().remove(); //remove row(td) from table with products in cart
        var total = 0;
        $('input.number-xs').each(function(){
            var count = parseInt(getCount(this)); //count of current product
            var price = parseFloat($(this).parent().next().text());
            total += price * count;
        });
        if(total == 0){
            $('button#order').attr('disabled', 'true');
        }
        $('#total').text(total.toFixed(2));
        $.ajax({
            url: location.href,
            type : "POST",
            data: {
                "param" : $(this).parent().parent().attr("id"),
                "count" : "0"}
        });
    });
    function getCount(e){
        return e.value;
    }
});

//increment/decrement product count, refresh total price
$(function(){
    $('input.number-xs').on("change", function () {
        //TODO replace some logic to button order
        var count = parseInt(getCount(this)); //count of current product
        var maxCount = parseInt($(this).attr('max')); //max count of current product
        if(count > maxCount){
            count = maxCount;
        } else if(count <= 0){
            count = 1; //controller removes product with count 0. In jsp product removes by button 'button.del-prod'
        }
        var total = 0;
        $('input.number-xs').each(function(){
            var count = parseInt(getCount(this)); //count of current product
            var price = parseFloat($(this).parent().next().text());
            total += price * count;
        });
        $('#total').text(total.toFixed(2));
        $.ajax({
            url: location.href,
            type : "POST",
            data: {
                "param" : $(this).parent().parent().attr("id"),
                "count" : count}
        });
    });
    function getCount(e){
        return e.value;
    }
});

//redirect to order page
$(function(){
    $('button#order').on("click", function(){
        location.href = "/order";
    });
});
/* End of Cart */

/* Login */
//button redirect to registration
$(function(){
   $('button#registration').on('click', function(){
       location.href = "/registration";
   });
});
/* End of Login */