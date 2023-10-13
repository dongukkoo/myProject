$(".category_button").click(function(){
    let categoryForm = $("#categoryForm");
    $(this).attr("type", "hidden");
    categoryForm.append($(this));
    categoryForm.submit();
    // let selectedCategory = $(this).val();
});