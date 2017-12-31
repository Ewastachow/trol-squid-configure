$(document).on('click', '.removedomain', function (e) {
    e.preventDefault();
    var listId = $('#listId').val();
    var url = "/domains/list/"+listId+"/edit"
    var domain = $(this).val();
    $.ajax({
        type: 'DELETE',
        url: url,
        contentType: "application/json",
        data: domain
    }).done(function(result){
        location.reload();
        //TODO
    });
    $(this).parent().parent().remove();
});

$(document).on('click', '.addDomain', function (e) {
    e.preventDefault();
    var input = $(this).parent().find(".domain");
    var listId = $('#listId').val();
    var url = "/domains/list/"+listId+"/edit"
    var domain = input.val();
    $.ajax({
        type: 'POST',
        url: url,
        contentType: "application/json",
        data: domain
    }).done(function(result){
        location.reload();
        //TODO
    });
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
    var td = tr.find(".domain");
    var newDomain = td.find(".newDomain").val();
    var oldDomain = td.find(".oldDomain").val();
    var listId = $('#listId').val();
    var url = "/domains/list/"+listId+"/edit"
    var data = {
        "oldValue" : oldDomain,
        "newValue" : newDomain
    };
    $.ajax({
        type:'PUT',
        url: url,
        contentType: "application/json",
        data: JSON.stringify(data)
    }).done(function(result){
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

