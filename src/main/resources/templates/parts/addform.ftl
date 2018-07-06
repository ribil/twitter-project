<#macro addform path>

<form method="post" action="/addmessage">
    <div class="input-group mb-3">

        <input type="text" class="form-control" name="text" placeholder="Добавить сообщение"
               aria-describedby="basic-addon2">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div class="input-group-append">
            <button class="btn btn-dark" type="submit">Добавить</button>
        </div>

    </div>
</form>
</#macro>