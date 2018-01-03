/**
 * AdminLTE Demo Menu
 * ------------------
 * You should not use this file in production.
 * This file is for demo purposes only.
 */
$(function () {
    $("#editPasswordForm-confirm").on('click', function() {
        var url = $(this).attr("data-check-url");
        // 校验原密码是否正确
        $.ajax({
            async: false,
            type: "POST",
            data: {"oldPassword":$("#inputOldPassword").val()},
            dataType: "json",
            url: url,
            success: function(data) {
                if (data.code == "200") {
                    $("#editPasswordForm").submit();
                } else {
                    alert(data.msg);
                }
            }
        });


    })

    $('#editPasswordForm')
        .bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
//                    valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                oldPassword: {
                    message: '必填',
                    validators: {
                        notEmpty: {
                            message: '必填'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: '必填'
                        }
                    }
                },
                passwordConfirm: {
                    validators: {
                        notEmpty: {
                            message: '必填'
                        },
                        identical: {
                            field: "password",
                            message: "两次输入密码不一致"
                        }
                    }
                }
            }
        })
        .on('success.form.bv', function(e) {
            // Prevent form submission
            e.preventDefault();

            // Get the form instance
            var $form = $(e.target);

            // Get the BootstrapValidator instance
            var bv = $form.data('bootstrapValidator');

            // Use Ajax to submit form data
            $.post($form.attr('action'), $form.serialize(), function(result) {
//                    console.log(result);
                if (result.code == "200") {
                    $("#modal-default-changepasswd").modal('hide');
                    $form
                        .bootstrapValidator('disableSubmitButtons', false)  // Enable the submit buttons
                        .bootstrapValidator('resetForm', true);             // Reset the form
                }
            }, 'json');
        });
})
