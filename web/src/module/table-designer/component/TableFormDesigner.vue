<template>
  <form-designer @submit="saveFormDefinition" :form-id="formId"/>
</template>

<script>
  import Vue from 'vue'
  import { Component, Prop } from 'vue-property-decorator'
  import { FormDesigner } from '../../form-designer'
  import { namespace } from 'vuex-class'

  const tableSpace = namespace('table')
  @Component({
    components: {
      FormDesigner
    }
  })
  export default class TableFormDesigner extends Vue {
    @Prop()
    tableId

    formId = null

    @tableSpace.Action('saveFormDefinition')
    save

    @tableSpace.Action('loadTable')
    loadTable

    created () {
      this.loadTable({ id: this.tableId }).then(({ data: { formId } }) => {
        this.formId = formId
      })
    }

    saveFormDefinition ({ id: formId }) {
      if (!this.formId) {
        this.save({ tableId: this.tableId, formId }).then(({ data }) => {
          this.formId = formId
        })
      }
    }
  }
</script>

<style scoped>

</style>
