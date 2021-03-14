package com.testm.demosdk

object DemoSdk {

    lateinit var arrayListOfAudios: ArrayList<Audio>

    fun getUniqueAndValidAudios(): ArrayList<Audio> {
        setValidUrls()
        setUniqueList()
        return arrayListOfAudios
    }

    private fun setValidUrls() {
        val itr = arrayListOfAudios.iterator()
        while (itr.hasNext()) {
            val audio = itr.next()
            if (!audio.url?.takeLast(4).equals(".wav")) itr.remove()
        }
    }

    private fun setUniqueList() {
        val uniqueArrayListOfAudios = ArrayList<Audio>()
        for (audio: Audio in arrayListOfAudios) {
            var isFound = false
            for (a: Audio in uniqueArrayListOfAudios) {
                if (a.url.equals(audio.url)) {
                    isFound = true
                    break;
                }
            }
            if (!isFound) uniqueArrayListOfAudios.add(audio)
        }
        arrayListOfAudios = uniqueArrayListOfAudios
    }
}