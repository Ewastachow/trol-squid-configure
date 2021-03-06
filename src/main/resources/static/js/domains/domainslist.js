function getErrorInfo(message) {
    return "<div class='alert alert-danger'>"+ message +"</div>";
}

$(document).on('click', '.removedomain', function (e) {
    e.preventDefault();
    var listId = $('#listId').val();
    var tr = $(this).parent().parent();
    var domainId = tr.find(".id").val();
    var url = "/domains/list/"+listId+"/edit/"+domainId;
    var domain = $(this).val();
    $.ajax({
        type: 'DELETE',
        url: url,
        contentType: "application/json",
    }).done(function(result){
        location.reload();
    });
    $(this).parent().parent().remove();
});


$(document).on('click', '.editdomain', function (e) {
    var tr = $(this).parent().parent();
    var domainDiv = tr.find(".domain");
    var domain = domainDiv.text();
    domainDiv.html(
        "<input type='text' class='newDomain' value='"+domain+"'/>"
    );
    var remove = tr.find(".removedomain");
    var edit = tr.find(".editdomain");

    edit.parent().html(
        "<button class='btn btn-success applyEdit'>Apply</button>")
    ;
    remove.parent().html(
        "<button class='btn btn-warning cancelEdit'>Cancel</button>"
    );
});

$(document).on('click', '.applyEdit', function (e) {
    var tr = $(this).parent().parent();
    var newDomain = tr.find(".newDomain").val();
    var domainId = tr.find(".id").val();
    var listId = $('#listId').val();
    var url = "/domains/list/"+listId+"/edit/"+domainId;
    $.ajax({
        type:'PUT',
        url: url,
        contentType: "application/json",
        data: newDomain
    }).done(function(result){
        location.reload();
    }).fail(function (xhr, status, error) {
        var response = JSON.parse(xhr.responseText);
        var error = response.error[0];
        //$("#adderror").html(getErrorInfo(error));
    });
});

$(document).on('click', '.cancelEdit', function (e) {
    var tr = $(this).parent().parent();
    var oldDomain = tr.find(".oldDomain").val();
    var domainDiv = tr.find(".domain");
    domainDiv.html(
        oldDomain
    );
    var cancel = tr.find(".cancelEdit");
    var apply = tr.find(".applyEdit");

    cancel.parent().html(
        "<button class='btn btn-danger removedomain'>Remove</button>"
    );
    apply.parent().html(
        "<button class='btn btn-default editdomain'>Edit</button>"
    );
});

