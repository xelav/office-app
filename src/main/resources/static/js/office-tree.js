<!-- item template -->

// define the item component
Vue.component('office-tree', {
    template: `
    <div>
        <a class="list-group-item list-group-item-action unselectable"
        :class="{'list-group-item-danger': selectedElement.type === 'office' && selectedElement.id === office.id}"
        @click="toggle"
        @dblclick="$emit('select-element', office, 'office')"
        >
            {{ office.name }}
            <i v-bind:class="[open ? 'fa fa-angle-down fa-lg' : 'fa fa-angle-right fa-lg']" v-if="isFolder" aria-hidden="true"></i>
        </a>
        <div class="list-group" v-show="open" v-if="isFolder">
            <a
                class="list-group-item list-group-item-action unselectable"
                :class="{'list-group-item-danger': selectedElement.type === 'subdivision' && selectedElement.id === subdivision.id}"
                v-for="subdivision in office.subdivisions"
                @dblclick="$emit('select-element', subdivision, 'subdivision')"
                >
                {{subdivision.name}}
            </a>
        </div>
    </div>
    `,
    mixins: [window.handleErrorMixin],
    props: {
        office: Object,
        selectedElement: Object,
    },
    data: function () {
        return {
            open: false
        }
    },
    computed: {
        isFolder: function () {
            return this.office.subdivisions &&
                this.office.subdivisions.length
        }
    },
    methods: {
        toggle: function () {
            if (this.isFolder) {
                this.open = !this.open
            }
        },
        select: function (id) {
            console.log(id);
        }
    }
});