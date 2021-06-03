package com.example.proyectokotlin

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController


class ThirdFragment : Fragment() {

    var posicion: Int = 0
    lateinit var etTitulo: EditText
    lateinit var etAutor: EditText
    lateinit var etGenero: EditText
    lateinit var etFecha: EditText
    lateinit var miLibro: Libro
    lateinit var etImagen: EditText
    lateinit var etLeido: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        posicion = arguments?.getInt("id") ?:-1
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)

        val bInsertar = view.findViewById<Button>(R.id.frag3_bInsertar)
        val bModificar = view.findViewById<Button>(R.id.frag3_bModificar)
        val bBorrar = view.findViewById<Button>(R.id.frag3_bBorrar)
        etTitulo = view.findViewById(R.id.frag3_etTitulo)
        etAutor = view.findViewById(R.id.frag3_etAutor)
        etGenero = view.findViewById(R.id.frag3_etGenero)
        etFecha = view.findViewById(R.id.frag3_etFecha)
        etImagen = view.findViewById(R.id.frag3_etImagen)
        etLeido = view.findViewById(R.id.frag3_etLeido)
        val tvId = view.findViewById<TextView>(R.id.frag3_tvId)

        if (posicion == -1){
            bBorrar.isEnabled = false
            bModificar.isEnabled = false
            bInsertar.isEnabled = true
            activity?.setTitle("Insertar libros")
        }else{
            bBorrar.isEnabled = true
            bModificar.isEnabled = true
            bInsertar.isEnabled = false
            activity?.setTitle("Modificar libros")
            (activity as MainActivity).miViewModel.buscarPorId(posicion)
            (activity as MainActivity).miViewModel.miLibro.observe(activity as MainActivity){
                libro -> libro?.let {
                    miLibro = it
                    tvId.text = String.format("Id: $posicion")
                    etTitulo.setText(libro.titulo)
                    etAutor.setText(libro.autor)
                    etGenero.setText(libro.genero)
                    etFecha.setText(libro.fecha)
                    etImagen.setText(libro.imagen)
                    etLeido.setText(libro.leido)
            }
            }
        }
        bInsertar.setOnClickListener {
            if (etTitulo.text.isEmpty() || etAutor.text.isEmpty() || etGenero.text.isEmpty() || etFecha.text.isEmpty() || etImagen.text.isEmpty() || etLeido.text.isEmpty()){
                Toast.makeText(activity, "Tienes que insertar todos los datos", Toast.LENGTH_SHORT).show()
            }else{
                (activity as MainActivity).miViewModel.insertar(Libro(titulo = etTitulo.text.toString(), autor = etAutor.text.toString(), genero = etGenero.text.toString(), fecha = etFecha.text.toString(), imagen = etImagen.text.toString(), leido = etLeido.text.toString()))
                findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
            }
        }
        bModificar.setOnClickListener {
            if (miLibro.titulo == etTitulo.text.toString() && miLibro.autor == etAutor.text.toString() && miLibro.genero == etGenero.text.toString() && miLibro.fecha == etFecha.text.toString() && miLibro.imagen == etImagen.text.toString() && miLibro.leido == etLeido.text.toString() ){
                Toast.makeText(activity, "No has modificado nada", Toast.LENGTH_SHORT).show()
            }else{
                (activity as MainActivity).miViewModel.actualizar(Libro(posicion, etTitulo.text.toString(), etAutor.text.toString(), etGenero.text.toString(), etFecha.text.toString(), etImagen.text.toString(), etLeido.text.toString()))
                findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
            }
        }
        bBorrar.setOnClickListener {
            (activity as MainActivity).miViewModel.borrarPorId(posicion)
            findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        if (posicion==-1){
            menu.findItem(R.id.modificar)?.isVisible=false
            menu.findItem(R.id.borrar)?.isVisible=false
        }
        else{
            menu.findItem(R.id.guardar)?.isVisible=false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.guardar->{
                if(etTitulo.text.isEmpty() || etAutor.text.isEmpty() || etGenero.text.isEmpty()||etFecha.text.isEmpty()|| etImagen.text.isEmpty()|| etLeido.text.isEmpty())
                    Toast.makeText(activity,"Tienes que insertar todos los datos", Toast.LENGTH_SHORT).show()
                else{
                    (activity as MainActivity).miViewModel.insertar(Libro(titulo= etTitulo.text.toString(),autor =  etAutor.text.toString(),genero =  etGenero.text.toString(),fecha =  etFecha.text.toString(), imagen = etImagen.text.toString(), leido = etLeido.text.toString()))
                    findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
                }
            }
            R.id.modificar->{
                if(miLibro.titulo==etTitulo.text.toString() && miLibro.autor == etAutor.text.toString() && miLibro.genero == etGenero.text.toString()&& miLibro.fecha == etFecha.text.toString() && miLibro.imagen==etImagen.text.toString()&& miLibro.leido==etLeido.text.toString()){
                    Toast.makeText(activity,"No has modificado nada", Toast.LENGTH_SHORT).show()
                }
                else{
                    (activity as MainActivity).miViewModel.actualizar(Libro(posicion, etTitulo.text.toString(),etAutor.text.toString(),etGenero.text.toString(),etFecha.text.toString(),etImagen.text.toString(),etLeido.text.toString()))
                    findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
                }
            }
            R.id.borrar->{
                (activity as MainActivity).miViewModel.borrarPorId(posicion)
                findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}