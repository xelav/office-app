function generateName(list, newObjName) {
    // funation for generating names for new objects like 'New Folder(3)'
    found = list.find(obj => {
        return obj.name === newObjName
    });

    if (!found) {
        return newObjName;
    }

    number = 2;
    while(true) {
        generatedName = newObjName + ' (' + number + ')';
        found = list.find(obj => {
            return obj.name === generatedName;
        });
        if (!found) {
            return generatedName;
        } else {
            number += 1;
        }
    }
}

Vue.prototype.$eventBus = new Vue()

Vue.http.options.root = '/api';

//TODO: properly rename
urls = {
    'office': 'offices',
    'subdivision': 'subdivisions',
    'worker': 'workers',
};

var mainApp = new Vue({
    el: '#main-app',
    mixins: [window.handleErrorMixin],
    data: {
        offices: [],
        selectedElement: {},
        workers: [],
    },
    computed:{
        selectIsEmpty() {
            return true
        }
    },
    methods: {
        loadOffices() {
            this.$http.get('offices')
                .then(response => {
                    this.offices = response.body.content;
                }, response => {
                    this.handleError(response);
                })
        },
        setSelectedElement(element, type) {
            console.log({element, type});
            this.selectedElement = element;
            this.selectedElement.type = type;

            // if (type === "subdivision")
            //     this.loadWorkers();
        },
        saveElement(element) {
            console.log(JSON.stringify(element, null, 2));
            this.$http.put(
                urls[element.type] + '/' + element.id,
                element
            ).then(response => {
                console.log('Success!', {response});
            }, response => {
                this.handleError(response);
            });
        },
        removeSelectedElement() {
            console.log(this.selectedElement);
            if (!(!Object.keys(this.selectedElement).length === 0
                && this.selectedElement.constructor === Object)) {
                console.log('asd2');
                this.$http.delete(
                    urls[this.selectedElement.type] + '/' + this.selectedElement.id
                ).then(response => {
                    console.log('Success!', {response});
                    location.reload()
                }, response => {
                    this.handleError(response);
                });
            } else {
                //TODO: some kind error message
            }
        },
        addOffice() {
            newName = generateName(this.offices, 'Новый офис');
            console.log({newName});
            this.$http.post(
                'offices',
                {
                    name: newName
                }
            ).then(response => {
                console.log('Success!', {response});
                location.reload();
            }, response => {
                this.handleError(response);
            });
        },
        addSubdivision(office) {
            newName = generateName(office.subdivisions, 'Новое подразделение');
            console.log({newName});
            this.$http.post(
                'subdivisions',
                {
                    name: newName,
                    office: office
                }
            ).then(response => {
                console.log('Success!', {response});
                location.reload()
            }, response => {
                this.handleError(response);
            });
        },
    },
    beforeMount() {
        this.loadOffices();
    },
})