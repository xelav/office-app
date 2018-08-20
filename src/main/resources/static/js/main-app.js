function generateName(list, newObjName) {
    // funation for generating names for new objects like 'New Folder(3)'
    found = list.find(obj => {
        return obj === newObjName
    });
    console.log({found})

    if (!found) {
        return newObjName;
    }

    number = 2;
    while(true) {
        generatedName = newObjName + ' (' + number + ')';
        found = list.find(obj => {
            return obj === generatedName;
        });
        if (!found) {
            return generatedName;
        } else {
            number += 1;
        }
    }
}

Vue.prototype.$eventBus = new Vue({})

Vue.http.options.root = '/api';

//TODO: properly rename
urls = {
    'office': 'offices',
    'subdivision': 'subdivisions',
    'worker': 'workers',
};

var mainApp = new Vue({
    el: '#main-app',
    mixins: [
        window.handleErrorMixin,
        window.handleSuccessMixin],
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
        setSelectedElement(elementId, level, parentId, nameList) {
            elementType = level == 0 ? 'office' : 'subdivision'
            this.$http.get(
                urls[elementType] + '/' + elementId
            ).then(response => {
                this.handleSuccess(response)
                this.selectedElement = {
                    ...response.body,
                    type: elementType,
                    nameList: nameList,
                };
            }, response => {
                this.handleError(response);
            });

            // if (type === "subdivision")
            //     this.loadWorkers();
        },
        saveElement(element) {
            console.log('Saving: ', {element})
            this.$http.put(
                urls[element.type] + '/' + element.id,
                element
            ).then(response => {
                this.handleSuccess(response)
                location.reload()
            }, response => {
                this.handleError(response);
            });
        },
        removeSelectedElement() {
            console.log(this.selectedElement);
            if (!(!Object.keys(this.selectedElement).length === 0
                && this.selectedElement.constructor === Object)) {
                this.$http.delete(
                    urls[this.selectedElement.type] + '/' + this.selectedElement.id
                ).then(response => {
                    this.handleSuccess(response)
                    location.reload()
                }, response => {
                    this.handleError(response);
                });
            } else {
                //TODO: some kind error message
            }
        },
        addOffice() {
            newName = generateName(window.officeNameList, 'Новый офис');
            console.log({newName});
            this.$http.post(
                'offices',
                {
                    name: newName
                }
            ).then(response => {
                this.handleSuccess(response)
                location.reload();
            }, response => {
                this.handleError(response);
            });
        },
        addSubdivision() {
            newName = generateName(this.selectedElement.nameList, 'Новое подразделение');
            console.log({newName});
            this.$http.post(
                'subdivisions',
                {
                    name: newName,
                    treeParent: this.selectedElement,
                }
            ).then(response => {
                this.handleError(response);
                location.reload()
            }, response => {
                this.handleError(response);
            });
        },
    },
    beforeMount() {
        //this.loadOffices();
    },
})