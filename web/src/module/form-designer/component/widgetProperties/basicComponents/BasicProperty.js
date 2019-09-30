import Vue from 'vue'
import { Component, Prop } from 'vue-property-decorator'

@Component
export default class BasicProperty extends Vue {
  @Prop({ default: () => ({ options: {}, rules: {} }) })
  widget

  get options () {
    return this.widget.options
  }

  get rules () {
    return this.widget.rules
  }
}
