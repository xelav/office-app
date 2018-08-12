<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home page</title>
    <link href="/css/main.css" rel="stylesheet">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/font-awesome.min.css">


</head>
<body>


<div id="main-app">
    <div class="split left">
        <div v-if="" class="container">
            <button v-on:click="addOffice" type="button" class="btn btn-light">Добавить офис</button>
            <button v-on:click="removeSelectedElement" type="button" class="btn btn-danger">Удалить элемент</button>
        </div>
        <hr/>
        <div class="container">
            <p style="color:#e6ebf0">Кликните дважды, чтобы выбрать элемент</p>
            <div class="just-padding">

                <div class="list-group list-group-root well">

                    <office-tree
                            v-for="office in offices"
                            :key="office.id"
                            :office="office"
                            :selected-element="selectedElement"
                            :class="{'selected-tree-element': selectedElement.type === 'office' && selectedElement.id == office.id}"
                            v-on:select-element="setSelectedElement"
                            :is-selected="selectedElement.id==office.id"
                    ></office-tree>

                </div>

            </div>
        </div>
    </div>
    <div class="split right">
        <div class="up">
            <div v-if="selectedElement.type == 'office'" class="container">
                <div class="form-group">
                    <label>Название офиса</label>
                    <input
                            type="text"
                            class="form-control"
                            id="office-name-input"
                            aria-describedby="basic-addon3"
                            v-model="selectedElement.name"
                    >

                    <label>Адрес</label>
                    <input
                            type="text"
                            class="form-control"
                            id="office-address-input"
                            aria-describedby="basic-addon3"
                            v-model="selectedElement.address"
                    >
                </div>

                <button v-on:click="saveElement(selectedElement)" type="button" class="btn btn-dark">Сохранить</button>
                <button v-on:click="addSubdivision(selectedElement)" type="button" class="btn btn-dark">Добавить подразделение</button>
            </div>

            <div v-if="selectedElement.type == 'subdivision'" class="container">
                <div class="form-group">
                    <label>Название подразделения</label>
                    <input
                            type="text"
                            class="form-control"
                            id="office-name-input"
                            aria-describedby="basic-addon3"
                            v-model="selectedElement.name"
                    >

                    <label>Имя руководителя</label>
                    <input
                            type="text"
                            class="form-control "
                            id="office-address-input"
                            aria-describedby="basic-addon3"
                            v-model="selectedElement.directorName"
                    >
                </div>

                <button v-on:click="saveElement(selectedElement)" type="button" class="btn btn-dark">Сохранить</button>
                <button v-on:click="$eventBus.$emit('create-worker', selectedElement)" type="button" class="btn btn-dark">Добавить сотрудника</button>
            </div>

        </div>
        <hr/>
        <div class="down container">
            <workers-table
                    :subdivision="selectedElement"
                    v-on:worker-created="addWorker(worker)"
            ></workers-table>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue-resource@1.5.0"></script>

<script>

    (function(global){

        global.handleErrorMixin = {
            methods: {
                handleError(response) {
                    console.log('Error!', {response});
                    console.log(response.body.message);
                }
            }
        }

    })(window);

</script>

<script src="/js/office-tree.js"></script>
<script src="/js/workers-table.js"></script>
<script src="/js/main-app.js"></script>

</body>
</html>