// Пример начального JavaScript для отключения отправки формы при наличии недопустимых полей
(function () {
    'use strict'

    window.addEventListener('load', function () {
        // Извлеките все формы, к которым мы хотим применить пользовательские стили проверки начальной загрузки
        var forms = document.getElementsByClassName('needs-validation')

        // Зацикливайтесь на них и предотвращайте отправку
        Array.prototype.filter.call(forms, function (form) {
            form.addEventListener('submit', function (event) {
                if (form.checkValidity() === false) {
                    event.preventDefault()
                    event.stopPropagation()
                }
                form.classList.add('was-validated')
            }, false)
        })

    }, false)
}())