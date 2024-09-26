import java.net.http.WebSocket.Listener

data class Cliente(val id: String, var nombre: String)
class Activity {
    private lateinit var clientes : MutableList<Cliente>
    init {
        val arrayOfClientes = arrayOf(
            Cliente("1", "Santi"),
            Cliente("2", "Sonia"),
            Cliente("3", "Guille"),
            Cliente("4", "Diego")
        )
        clientes = arrayOfClientes.toMutableList()
        println("Lista inicial de clientes: $clientes")
    }
    fun addCliente(id: String, nombre: String) {
        val nuevoCliente = Cliente(id, nombre)
        clientes.add(nuevoCliente)
        println("Cliente añadido: $nuevoCliente")
        println("Lista actualizada de clientes: $clientes")
    }
    fun deleteCliente(id: String, nombre: String){
        val clienteEliminado = Cliente(id, nombre)
        clientes.remove(clienteEliminado)
        println("Cliente eliminado: $clienteEliminado")
        println("Lista actualizada de clientes: $clientes")
    }
    fun updateCliente(id: String, nombre: String){
        for (cliente in clientes){
            if (cliente.id == id)
                cliente.nombre = nombre
            break
        }
        println("Cliente actualizado, nuevo nombre: $nombre")
        println("Lista actualizada de clientes: $clientes")
    }
    // Simulación del click del butón por mi.
    fun onButtonClickAdd() {
        val dialogo = ClienteDialogFragment()
        dialogo.setOnClienteAddedListener { id, nombre ->
            addCliente(id, nombre)
        }
        dialogo.mostrarAdd()
    }
    fun onButtonClickDelete(){
        val dialogo = ClienteDialogFragment()
        dialogo.setOnDeleteClienteListener { id, nombre ->
            deleteCliente(id, nombre)
        }
        dialogo.mostrarDelete()
    }
    fun onUpdateClickDelete(){
        val dialogo = ClienteDialogFragment()
        dialogo.setOnUpdateClienteListener { id, nombre ->
            updateCliente(id, nombre)
        }
        dialogo.mostrarUpdate()
    }
}
// Simulamos nuestro dialogo chorrero
class ClienteDialogFragment {
    private var onClienteAdded: ((String, String) -> Unit)? = null
    private var onDeleteCliente: ((String, String) -> Unit)? = null
    private var onUpdateCliente: ((String, String) -> Unit)? = null
    //Metodo que inicializa el listener.
    fun setOnClienteAddedListener(listener: (String, String) -> Unit) {
        onClienteAdded = listener
    }
    fun setOnDeleteClienteListener(listener: (String, String) -> Unit){
        onDeleteCliente = listener
    }
    fun setOnUpdateClienteListener(listener: (String, String) ->Unit){
        onUpdateCliente = listener
    }
    fun mostrarAdd() {
        val id = "4"
        val nombre = "Pepito"
        println("Simulando el diálogo con los datos ingresados: id=$id, nombre=$nombre")
        if (onClienteAdded != null) {
            onClienteAdded!!(id, nombre)
        }
    }
    fun mostrarDelete(){
        val id = "3"
        val nombre = "Guille"
        println("Simulando el diálogo con los datos ingresados: id=$id, nombre=$nombre")
        if (onDeleteCliente != null)
            onDeleteCliente!!(id,nombre)
    }
    fun mostrarUpdate(){
        val id = "1"
        val nombre = "nuevo nombre"
        println("Simulando el diálogo con los datos ingresados: id=$id, nombre=$nombre")
        if (onUpdateCliente != null)
            onUpdateCliente!!(id, nombre)
    }
}
fun main() {
    val activity = Activity()
// Simular el clic en un botón que abre el diálogo
    println("Simulando clic en el botón para abrir el diálogo...")
    activity.onButtonClickAdd()
    println("===============================================================================")
    activity.onButtonClickDelete()
    println("===============================================================================")
    activity.onUpdateClickDelete()
    println("===============================================================================")

    println("Esperando 2 segundos ...")
    Thread.sleep(2000)
    println("Fin")
}

/*
*        ============================================DUDAS============================================
*DUDA 1
*
*lateinit no tiene mucho sentido porque es una lista mutable y se puede cambiar, sino si que tendria sentido
*
* ===========================================================================================================
*
* DUDA 2
*
* en setOnUpdateClienteListener(listener: (String, String) ->Unit) devuelve unit porque el evento que dispara el
* funcionamiento de la funcion, updateClient, es void.
*
* ==============================================================================================================
*
* DUDA 3
*
* en un ejemplo real existiría un metodo mostrarAdd? si es así como se haría
*
* fun onButtonClickAdd() {
        val dialogo = ClienteDialogFragment()
        dialogo.setOnClienteAddedListener { id, nombre ->
            addCliente(id, nombre)
        }
        dialogo.mostrarAdd()---------------------
    }
    *
    *
*/