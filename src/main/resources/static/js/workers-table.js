<!-- item template -->

// define the item component
Vue.component('workers-table', {
    template: `
    <!-- Check if current subdivision is actually a subdivision -->
    <div v-if="subdivision.type == 'subdivision'" class="container">
        <nav class="pull-right">
          <ul class="pagination">
            <li class="page-item"><a class="page-link" v-on:click="pageNumber = 0"><i class="fa fa-angle-double-left fa-lg" aria-hidden="true"></i></a></li>
            <li class="page-item"><a class="page-link" v-on:click="pageNumber = Math.max(0, pageNumber-1)"><i class="fa fa-angle-left fa-lg" aria-hidden="true"></i></a></li>
            
            <li v-if="pageNumber+1>1" class="page-item"><a class="page-link" v-on:click="pageNumber -= 1">{{this.pageNumber}}</a></li>
            <li  class="page-item active"><a class="page-link">{{this.pageNumber+1}}</a></li>
            <li v-if="pageNumber+1<totalPages" class="page-item"><a class="page-link" v-on:click="pageNumber += 1">{{this.pageNumber+2}}</a></li>

            <li class="page-item"><a class="page-link" v-on:click="pageNumber = Math.min(totalPages-1, pageNumber+1)" ><i class="fa fa-angle-right fa-lg" aria-hidden="true"></i></a></li>
            <li class="page-item"><a class="page-link" v-on:click="pageNumber = totalPages-1"><i class="fa fa-angle-double-right fa-lg" aria-hidden="true"></i></a></li>
          </ul>
        </nav>
        <table class="table">
            <thead class="thead-dark">
                <th
                    v-for="field in fields"
                    v-on:click="setSortField(field.name)"
                 >  {{field.label}}
                    <i v-bind:class="[getIconClass(field.name)]"  aria-hidden="true"></i>
                </th>
                
                <th></th>
                <th></th>
            </thead>
            <tbody>
                <tr v-for="worker in workers">
                    <td><input v-model="worker.name" type="text" class="form-control" /></td>
                    <td><input v-model="worker.birthDate" type="text" class="form-control" /></td>
                    <td><input v-model="worker.email" type="text" class="form-control" /></td>
                    <td><input v-model="worker.phoneNumber" type="text" class="form-control" /></td>
                    <td><button v-on:click="saveWorker(worker)" type="button" class="btn btn-dark">Сохранить</button></td>
                    <td><button v-on:click="deleteWorker(worker)" type="button" class="btn btn-danger">Удалить</button></td>
                </tr>
            </tbody>
        </table>    
    </div>
    
    `,
    mixins: [window.handleErrorMixin],
    props: {
        subdivision: Object,
        fields: {
            type:Array,
            default: function () {
                return [
                    {name: 'name', label: 'Имя'},
                    {name: 'birthDate', label: 'Дата рождения'},
                    {name: 'email', label: 'Электронная почта'},
                    {name: 'phoneNumber', label: 'Номер телефона'},
                ]
            }
        },
    },
    data: function() { return {
        workers: [],

        sortField: 'name',
        sortDestination: 'ASC',
        pageNumber: 0,
        totalPages: 0,
    }},
    methods: {
        loadWorkers() {
            console.log('loadWorkers()')
            this.$http.get(
                'subdivisions/' + this.subdivision.id + '/workers',
                {params: {
                        page: this.pageNumber,
                        sortField: this.sortField,
                        sortDirection: this.sortDestination,
                    }}
            ).then(response => {
                console.log(response)
                this.workers = response.body.content;
                this.totalPages = response.body.totalPages;
            }, response => {
                this.handleError(response);
            })
        },
        saveWorker(worker) {
            console.log(JSON.stringify(worker, null, 2));
            this.$http.put(
                'workers/' + worker.id,
                worker
            ).then(response => {
                console.log('Success!', {response});
            }, response => {
                this.handleError(response);
            });
        },
        deleteWorker(worker) {
            console.log("ASD")
            this.$http.delete(
                'workers/' + worker.id,
                worker
            ).then(response => {
                console.log('Success!', {response});
                this.workers = this.workers.filter(function(e) { return e.id != worker.id })
            }, response => {
                this.handleError(response);
            });
        },
        addWorker(worker) {
            this.$http.post(
                'workers',
                {
                    name: 'Новый работник',
                    subdivision: this.subdivision
                }
            ).then(response => {
                console.log('Success!', {response});
                this.loadWorkers();
            }, response => {
                this.handleError(response);
            });
        },
        setSortField(fieldName){
            console.log({fieldName})
            if (fieldName !== this.sortField){
                this.sortField = fieldName;
                this.sortDestination = 'ASC';
            } else {
                if (this.sortDestination === 'ASC')
                    this.sortDestination = 'DESC'
                else
                    this.sortDestination = 'ASC'
            }
        },
        getIconClass(fieldName){
            if (fieldName === this.sortField) {
                if (this.sortDestination === 'ASC'){
                    return 'fa fa-sort-asc'
                } else if (this.sortDestination === 'DESC') {
                    return 'fa fa-sort-desc'
                }
            } else {
                return 'fa fa-sort'
            }
        }
    },
    watch: {
        subdivision: function () {
            this.loadWorkers();
        },
        sortField: function () {
            this.loadWorkers();
        },
        sortDestination: function () {
            this.loadWorkers();
        },
        pageNumber: function () {
            this.loadWorkers();
        },
    },
    created() {
        this.$eventBus.$on('create-worker', this.addWorker)
    },
    beforeDestroy() {
        this.$eventBus.$off('create-worker')
    },

});