function getListeLieux() {
    $.ajax({
        type: "POST",
        url: "ajax",
        data: {
            'categorie': "getListeLieux"

        },
        success: function(html) {
            $("#displayListeLieux").html(html).show();
            $("#table").bootstrapTable();
        }
    });
}

$(document).ready(function() {
    //$("#displayListeLieux").html('<tr><td colspan="5"><strong>Chargement...</strong></td></tr>');

    //getListeLieux();

    $("#refreshListeLieux").click(function (){
        $("#displayListeLieux").html('<tr><td colspan="5"><strong>Chargement...</strong></td></tr>');
        getListeLieux();
    });

});