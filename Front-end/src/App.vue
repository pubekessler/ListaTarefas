<template>
  <div id="app">

    <nav>
      <div class="nav-wrapper blue darken-1">
        <a href="#" class="brand-logo center">Listas da ACME</a>
      </div>
    </nav>

    <div class="container">

      <form>

          <label>Nome</label>
          <input type="text" placeholder="Nome" v-model="lista.nome"> 
         

          <button class="waves-effect waves-light btn-small" @click.prevent="criarLista">Criar Lista<i class="material-icons left">save</i></button>

      </form> 

      <table>

        <thead>

          <tr>
            <th>NOME</th>
          
          
            <th>OPÇÕES</th>
          </tr>

        </thead>

        <tbody>

   
          <tr v-for="(lista, index) in this.listas" :key="lista.id">
          <button class="waves-effect btn-small blue darken-1"  @click.prevent="selecionarLista(lista)"><i class="material-icons">arrow_downward</i></button>
            <td> {{ lista.nome }}</td>
         
            <td>
             
              <button class="waves-effect btn-small red darken-1" @click.prevent="deletarLista(lista.id, index)"><i class="material-icons">delete_sweep</i></button>
            </td>

          </tr>

        </tbody>
      
      </table>

    </div>


<!-- itens -->
<div v-if="listaSelecionada.id != null">
     <nav>
      <div class="nav-wrapper green darken-1">
        <a href="#" class="brand-logo center">LISTA : {{ listaSelecionada.nome }}</a>
      </div>
    </nav>
    <div class="container">

      <form>

          <label>Item</label>
          <input type="text" placeholder="Item" v-model="item.nome"> 
         

          <button class="waves-effect waves-light btn-small" @click.prevent="criarItem">Criar Item<i class="material-icons left">save</i></button>

      </form> 

      <table>

        <thead>

          <tr>
            <th>NOME</th>
          
          
            <th>OPÇÕES</th>
          </tr>

        </thead>

        <tbody>

   
          <tr v-for="(item, index) in this.itens" :key="item.id">
            <button v-if=item.realizada class="waves-effect btn-small green darken-1" @click.prevent="concluirItem(item.id, index)"><i class="material-icons">assignment_turned_in</i></button>
          <button v-else class="waves-effect btn-small blue darken-1" @click.prevent="concluirItem(item.id, index)"><i class="material-icons">assignment_late</i></button>
           

            <td v-if=item.realizada style='text-decoration: line-through;'> {{ item.nome }}</td>
              <td v-else > {{ item.nome }}</td>
            
         
            <td>
              
              <button class="waves-effect btn-small red darken-1" @click.prevent="deletarItem(item.id, index)"><i class="material-icons">delete_sweep</i></button>
            </td>

          </tr>

        </tbody>
      
      </table>

    </div>
</div>
  </div>
</template>

<script>
import Axios from "axios"
import Vue from 'vue'

var url = "http://127.0.0.1:8090"
export default {
   data() {
     return {
       listas: [],
       lista : {
         nome : "",
         id : null,
       },
       listaSelecionada : {
         nome : "",
         id : null,
       },
       itens : [],
        item : {
         nome : "",
         id : null,
         realizada : false,
         lista : {
           nome : "",
         id : null,
         }
       },
     }
   
  },
  mounted(){
    Axios.get(url+"/lista").then(response => {
      console.log(response);
    this.listas = response.data;

    console.log(this.listas);
    })
  },
methods: {
 criarLista() {
  
    console.log(JSON.stringify(this.lista));
    
  Axios.post(url+"/lista", this.lista)
    .then(response => {
      console.log(response);
      this.listas.push(response.data);
      this.lista.nome = "";
    });

  },
  criarItem() {
  
    console.log(JSON.stringify(this.item));
    
    this.item.lista.id = this.listaSelecionada.id;
  Axios.post(url+"/item", this.item)
    .then(response => {
      console.log(response);
      this.itens.push(response.data);
      this.item.nome = "";
    });

  },
  selecionarLista(index) {
    this.listaSelecionada=index;
     Axios.get(url+"/item/porLista/"+this.listaSelecionada.id).then(response => {
      console.log(response);
    this.itens = response.data;

    console.log(this.itens);
    })
  },
  deletarLista(id,index) {
  
      
  Axios.delete(url+"/lista/"+id)
    .then(response => {
      console.log(response);
      this.$delete(this.listas, index);  
    });

  },
  deletarItem(id,index) {
  
      
  Axios.delete(url+"/item/"+id)
    .then(response => {
      console.log(response);
      this.$delete(this.itens, index);  
    });

  },
   concluirItem(id, index) {
  
     
  Axios.post(url+"/item/concluir/"+id)
    .then(response => {
      console.log(response);
      Vue.set(this.itens, index, response.data);
    });

  }
}
  
  
}


</script>

<style>
</style>


