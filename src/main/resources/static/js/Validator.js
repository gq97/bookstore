var form1 = $('#registerForm');
var form2 = $('#updateForm');
$(document).ready(function () {
    form1.bootstrapValidator({
        message: '输入值不合法',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                message: '用户名不合法',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 15,
                        message: '请输入1到15个字符'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\. \u4e00-\u9fa5 ]+$/,
                        message: '用户名只能由字母、数字、点、下划线和汉字组成 '
                    }
                }
            },
            password: {
                message: '密码不合法',
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 5,
                        max: 20,
                        message: '请输入5到20个字符'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9]+$/,
                        message: '密码只能由字母、数字组成 '
                    }
                }
            }
            ,confirmPassword: {
                validators: {
                    identical: {
                        field: 'password',
                        message: '确认密码与密码不一致！'
                    },
                    notEmpty: {
                        message: '用户确认密码不能为空'
                    },
                    stringLength: {
                        min: 5,
                        max: 20,
                        message: '用户确认密码长度大于5小于20'
                    },

                    regexp: {
                        regexp: /^[a-zA-Z0-9]+$/,
                        message: '密码只能由字母、数字组成 '
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: 'email不能为空'
                    },
                    emailAddress: {
                        message: '请输入正确的邮件地址如：123@qq.com'
                    }
                }
            }
        }
    });
    form2.bootstrapValidator({
        message: '输入值不合法',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            oldPassword: {
                message: '原密码不合法',
                validators: {
                    notEmpty: {
                        message: '用户旧密码不能为空'
                    },
                    stringLength: {
                        min: 5,
                        max: 19,
                        message: '用户旧密码长度必须大于5且小于20'
                    },
                    regexp: {
                        regexp: /^[^ ]+$/,
                        message: '用户旧密码不能有空格'
                    }
                }
            },
            newPassword: {
                message: '密码不合法',
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 5,
                        max: 20,
                        message: '请输入5到20个字符'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9]+$/,
                        message: '密码只能由字母、数字组成 '
                    }
                }
            },
            confirmNewPassword: {
                validators: {
                    identical: {
                        field: 'newPassword',
                        message: '确认密码与密码不一致！'
                    },
                    notEmpty: {
                        message: '用户确认密码不能为空'
                    },
                    stringLength: {
                        min: 5,
                        max: 20,
                        message: '用户确认密码长度大于5小于20'
                    },

                    regexp: {
                        regexp: /^[a-zA-Z0-9]+$/,
                        message: '密码只能由字母、数字组成 '
                    }
                }
            }

        }
    });
});

$("#submitBtn").click(function() {
    //进行表单验证
    var bv = form1.data('bootstrapValidator');
    bv.validate();
    if(bv.isValid()) {
        //发送ajax请求
        $.ajax({
            url: '/register',
            async: false, //同步，会阻塞操作
            type: 'post', //PUT DELETE POST
            data: form1.serialize(),
            success: function(result){
                if(result == true) {
                    $("#returnMessage").hide().html('<a data-toggle="modal" href="#myModal2" class="label label-success">注册成功，快去登录吧</a>').show(300);
                } else {
                    $("#returnMessage").hide().html('<label class="label label-warning">该用户名或者邮箱已被注册</label>').show(300);
                }
            }, error: function() {
                $("#returnMessage").hide().html('<label class="label label-warning">该用户名或者邮箱已被注册</label>').show(300);
            }
        })
    }
});

$("#submitBtn2").click(function() {
    //进行表单验证
    var bv = form2.data('bootstrapValidator');
    bv.validate();
    if(bv.isValid()) {
        //发送ajax请求
        $.ajax({
            url: '/updateUser',
            async: false, //同步，会阻塞操作
            type: 'POST', //PUT DELETE POST
            data: form2.serialize(),
            success: function(result){
                if(result) {
                    $("#returnMessage2").hide().html('<label class="label label-success">修改成功</label>').show(300);
                } else {
                    $("#returnMessage2").hide().html('<label class="label label-warning">修改失败，请输入正确的原密码</label>').show(300);
                }
            }, error: function() {
                $("#returnMessage2").hide().html('<label class="label label-warning">修改失败，请输入正确的原密码</label>').show(300);
            }
        })
    }
});
