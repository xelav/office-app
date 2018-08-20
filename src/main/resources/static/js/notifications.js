Vue.component("notifications", {
    template: `
    <div>

      <div class="notifications">
        <div class="list-group">
          <div v-for="item in items" class="panel" :class="'panel-' + item.alert">
            <div class="panel-heading">
              <i class="fa" :class="item.icon"></i>
            </div>
            <div class="panel-body text-center">
              <span>{{ item.message }}</span>
            </div>
            <div class="panel-footer text-center">
              <a href="#" v-on:click.prevent="remove(item.id)">Убрать</a>
            </div>
          </div>
        </div>
      </div>

    </div>
    
    `,
    data: function() {
        return {
            id: 1,
            items: []
        };
    },
    methods: {
        add: function(message, alert, icon) {
            var t = this;
            var id = this.id++;
            this.items.push({
                id: id,
                message: message,
                alert: alert,
                icon: icon,
                event: setTimeout(function() {
                    t.remove(id);
                }, 10000)
            });
        },
        remove: function(id) {
            if (this.items) {
                for (var i = 0; i < this.items.length; i++) {
                    if (this.items[i].id == id) {
                        clearTimeout(this.items[i].event);
                        this.items.splice(i, 1);
                        break;
                    }
                }
            }
        }
    },

    created() {
        this.$eventBus.$on('alert', (d) => {
            this.add(d.message, d.alert, d.icon);
        })
    },
    beforeDestroy() {
        this.$eventBus.$off('alert')
    },
});