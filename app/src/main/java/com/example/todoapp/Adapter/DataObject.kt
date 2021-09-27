package com.example.todoapp.Adapter

object DataObject {
    var listData = mutableListOf<CardInfo>()


    fun setData(title:String,priority:String){
listData.add(CardInfo(title,priority))

    }


    fun getAllData():List<CardInfo>{
return listData
    }

    fun deleteAll(){
        listData.clear()
    }


    fun getData(posit:Int):CardInfo{
        return listData[posit]
    }


    fun deleteData(posit:Int){
        listData.removeAt(posit)
    }

    fun updateData(posit:Int,title:String,priority:String){

        listData[posit].title = title
        listData[posit].priority = priority


    }



}