$(document).on('click', '.removedomain', function (e) {
    e.preventDefault();
    $.ajax({
        type:'DELETE',
        url:'blacklist',
        contentType: "application/json",
        data:($(this).val())
    }).success(function(result){
        //TODO
    });
    $(this).parent().parent().remove();
});

$(document).on('click', '.editdomain', function (e) {
    var tr = $(this).parent().parent();
    var td = $(this).parent().parent().find(".domain");
    var domain = td.text();
    var newDomain = "<input type='text' class='newDomain' value='"+domain+"'/>";
    var oldDomain = "<input type='hidden' class='oldDomain' value='"+domain+"'/>";
    var applyEdit = "<td><button class='applyEdit'>Apply</button></td>";
    var cancelEdit = "<td><button class='cancelEdit'>Cancel</button></td>";
    tr.html(
      '<td class="domain">'+newDomain+oldDomain+'</td>'+applyEdit+cancelEdit
    );
});

$(document).on('click', '.applyEdit', function (e) {
    var tr = $(this).parent().parent();
    var td = $(this).parent().parent().find(".domain");
    var newValue = td.find(".newDomain").val();
    var oldValue = td.find(".oldDomain").val();
    var data = {
        "oldValue" : oldValue,
        "newValue" : newValue
    };
    $.ajax({
        type:'PUT',
        url:'blacklist',
        contentType: "application/json",
        data:JSON.stringify(data)
    }).success(function(result){
        location.reload();
        //TODO show result
    });
});

$(document).on('click', '.cancelEdit', function (e) {
    var tr = $(this).parent().parent();
    var td = $(this).parent().parent().find(".oldDomain");
    var domain = td.val();
    var newDomain = '<td class="domain">'+domain+'</td>';
    var removeDomain = '<td><button class="removedomain" value='+domain+'>Remove</button></td>';
    var editDomain = '<td><button class="editdomain" value='+domain+'>Edit</button></td>';
    tr.html(
      newDomain+removeDomain+editDomain
    );
});

