var form = $('#registerForm');
$(document).ready(function () {
    form.bootstrapValidator({
        message: '输入值不合法',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                message: '用户名不合法',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 15,
                        message: '请输入6到15个字符'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\. \u4e00-\u9fa5 ]+$/,
                        message: '用户名只能由字母、数字、点、下划线和汉字组成 '
                    }
                }
            }
            , email: {
                validators: {
                    notEmpty: {
                        message: 'email不能为空'
                    },
                    emailAddress: {
                        message: '请输入正确的邮件地址如：123@qq.com'
                    }
                }
            }, phone: {
                validators: {
                    notEmpty: {
                        message: '手机号不能为空'
                    },
                    regexp: {
                        regexp: "^([0-9]{11})?$",
                        message: '手机号码格式错误'
                    }
                }
            }, address: {
                validators: {
                    notEmpty: {
                        message: '地址不能为空'
                    }, stringLength: {
                        min: 8,
                        max: 60,
                        message: '请输入5到60个字符'
                    }
                }
            }
            /*验证：规则*/
            ,oldPassword: {
                validators: {
                    notEmpty: {
                        message: '用户旧密码不能为空'
                    },
                    stringLength: {
                        min: 5,
                        max: 19,
                        message: '用户旧密码长度大于5小于20'
                    },
                    regexp: {
                        regexp: /^[^ ]+$/,
                        message: '用户旧密码不能有空格'
                    }
                }
            }
            ,newPassword: {
                validators: {
                    notEmpty: {
                        message: '用户新密码不能为空'
                    },
                    identical: {
                        field: 'comfirmPassword',
                        message: '用户新密码与确认密码不一致！'
                    },
                    stringLength: {
                        min: 5,
                        max: 20,
                        message: '用户新密码长度大于5小于20'
                    },
                    regexp: {
                        regexp: /^[^ ]+$/,
                        message: '用户新密码不能有空格'
                    }

                }
            }
            ,confirmPassword: {
                validators: {
                    identical: {
                        field: 'newPassword',
                        message: '用户新密码与确认密码不一致！'
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
                        regexp: /^[^ ]+$/,
                        message: '用户确认密码不能有空格'
                    }
                }
            }
        }
    });
});

