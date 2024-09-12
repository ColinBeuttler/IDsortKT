package com.colinbeuttler.sortdisplay

import models.ListId
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.util.*


object Main {
    // create new arraylist to store the objects
    var ids: ArrayList = ArrayList<ListId>()

    // open Scanner
    var scan = Scanner(System.`in`)

    @JvmStatic
    fun main(args: Array<String>) {

        // fetch the Url here
        try {
            // create a String for the URL
            val u = "https://fetch-hiring.s3.amazonaws.com/hiring.json"
            val url = URL(u)

            // Open Url stream and read the URL content
            val br = BufferedReader(InputStreamReader(url.openStream()))

            // String to store each line of content
            var inputLine: String

            
            while (br.readLine().also { inputLine = it } != null) { // while there is a next line of content
        
                val newIString = inputLine.replace("[^0-9,]".toRegex(), "")  // remove all the excess char to prepare to split the string
                if (!newIString.isEmpty() || newIString == null) { // check for any lines that do not have usable data
                    val stringList: Array<String?> =// create String array that will be used for each object
                        newIString.split(",".toRegex(), limit = 4).toTypedArray()
                    if (!stringList[2]!!.isEmpty() || stringList[2] == null) { // checks if the stringList[2] has usable data          
                        ids.add(
                            ListId(
                                stringList[0]!!.toInt(), stringList[1]!!.toInt(), stringList[2]!!
                                    .toInt() // put each string array into my object and add them to the arraylist
                            )
                        )
                    }
                }
            }
            br.close()
        } // catch url errors
        catch (e: IOException) {
            e.printStackTrace()
        }
        // sort arrayList by ListId id value

        val sortedList = list.sortedWith(
            compareBy<ListId>{
                ids.listId?.toIntOrNull() ?: 0;
            }.thenBy {
                ids.name?.toIntOrNull() ?: 0;
            }
        )
        // Collections.sort(ids, object : Comparator<ListId?> {
        //     fun compare(o1: ListId, o2: ListId): Int {
        //         return if (o1.listId == o2.listId) {
        //             if (o1.name < o2.name) -1 else 1
        //         } else if (o1.listId < o2.listId) -1 else 1
        //     }

        //     override fun compare(o1: ListId?, o2: ListId?): Int {
        //         TODO("Not yet implemented")
        //     }
        // })
        // Print all lines of the ArrayList of Obj to the terminal
        val iterator: Iterator<*> = sortedList.iterator()
        while (iterator.hasNext()) println(iterator.next())
        // }
    }
}