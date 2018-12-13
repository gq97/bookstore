var id = -1;
$(document).ready(function(){
    var newReceiveRadio = document.getElementById("newReceiveRadio");
    var newAddress = document.getElementById("newAddress");
    var radios = document.getElementsByName("radio");
    if(radios.length == 0) {
        newReceiveRadio.checked = true;
        newAddress.style.display = "inline";
    } else {
        if(newReceiveRadio.checked == true) {
            newAddress.style.display = "inline";
        } else {
            newAddress.style.display = "none";
        }
    }
})
function usePreAddress() {
    var newAddress = document.getElementById("newAddress");
    newAddress.style.display = "none";
}

function addReceive() {
    var receive_name = document.getElementById("receive_name").value;
    var receive_address = document.getElementById("receive_address").value;
    var receive_street = document.getElementById("receive_street").value;
    var receive_phone = document.getElementById("receive_phone").value;
    if(id == -1) {
        $.ajax({
            type: 'post',
            url: '/addReceive',
            data: {
                receive_name: receive_name,
                receive_address: receive_address,
                receive_street: receive_street,
                receive_phone: receive_phone
            },
            success: function (result) {
                alert("提交成功");
                window.location.reload();
            }
        });
    } else {
        $.ajax({
            type: 'post',
            url: '/updateReceive',
            data: {
                receive_id: id,
                receive_name: receive_name,
                receive_address: receive_address,
                receive_street: receive_street,
                receive_phone: receive_phone
            },
            success: function (result) {
                alert("更新成功");
                window.location.reload();
            }
        });
    }

};

function useNewAddress() {
    var newAddress = document.getElementById("newAddress");
    newAddress.style.display = "inline";
    id = -1;
};

function updateReceive(receive_id) {
    var receive_name = document.getElementById("receive_name"+receive_id).value;
    var receive_address = document.getElementById("receive_address"+receive_id).value;
    var receive_street = document.getElementById("receive_street"+receive_id).value;
    var receive_phone = document.getElementById("receive_phone"+receive_id).value;
    var newAddress = document.getElementById("newAddress");
    var currentRadio = document.getElementById("radio"+receive_id);
    currentRadio.checked = true;
    newAddress.style.display = "inline";
    document.getElementById("receive_name").value = receive_name;
    document.getElementById("receive_address").value = receive_address;
    document.getElementById("receive_street").value = receive_street;
    document.getElementById("receive_phone").value = receive_phone;
    id = receive_id;
};

function updateReceive_default(receive_id) {
    $.ajax({
        url: '/updateReceive_default',
        data: {receive_id: receive_id},
        success: function(result) {
            if(result == true) {
                alert("成功设为默认地址");
                window.location.reload();
            }
        }
    })
};