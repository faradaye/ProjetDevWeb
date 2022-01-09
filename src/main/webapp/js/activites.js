function getListeActivites() {
    $.ajax({
        type: "POST",
        url: "ajax",
        data: {
            'categorie': "getListeActivites"

        },
        success: function(html) {
            $("#displayListeActivites").html(html).show();
            $("#table").bootstrapTable();
        }
    });
}

$(document).ready(function() {
    //$("#displayListeActivites").html('<tr><td colspan="6"><strong>Chargement...</strong></td></tr>');

    //getListeActivites();



    $("#refreshListeActivite").click(function (){
        $("#displayListeActivites").html('<tr><td colspan="6"><strong>Chargement...</strong></td></tr>');
        getListeActivites();
    });

});