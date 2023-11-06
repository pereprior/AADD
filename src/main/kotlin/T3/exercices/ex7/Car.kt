package T3.exercices.ex7

class CarModel(var brand:String,var model:String,var color:String)
class CarEngine(var name:String,var fuel:String)
class ExtraFeatures(var name:String,var value:Double)
class CarPrice(var initialPrice:Double,var offerPrice:Double,var extras:ArrayList<ExtraFeatures>)
class Car(var model:CarModel, var engine:CarEngine, var id:String, var kilometers:Int, var price:CarPrice, var pictures:ArrayList<String>)
class CarList(var rutes: MutableList<Car> = mutableListOf()) {

    fun setBrand(index:Int,brand:String) {this.rutes[index].model.brand=brand}
    fun getBrand(index:Int):String {return this.rutes[index].model.brand}
    fun setModel(index:Int,model:String) {this.rutes[index].model.model=model}
    fun getModel(index:Int):String {return this.rutes[index].model.model}
    fun setColor(index:Int,color:String) {this.rutes[index].model.color=color}
    fun getColor(index:Int):String {return this.rutes[index].model.color}
    fun setEngine(index:Int,engine:String) {this.rutes[index].engine.name=engine}
    fun getEngine(index:Int):String {return this.rutes[index].engine.name}
    fun setFuel(index:Int,fuel:String) {this.rutes[index].engine.fuel=fuel}
    fun getFuel(index:Int):String {return this.rutes[index].engine.fuel}
    fun setKilometer(index:Int,km:Int) {this.rutes[index].kilometers=km}
    fun getKilometer(index:Int):Int {return this.rutes[index].kilometers}
    fun setPrice(index:Int,price:Double) {this.rutes[index].price.initialPrice=price}
    fun getPrice(index:Int):Double {return this.rutes[index].price.initialPrice}
    fun setOffer(index:Int,offer:Double) {this.rutes[index].price.offerPrice=offer}
    fun getOffer(index:Int):Double {return this.rutes[index].price.offerPrice}
    fun setPicture(index: Int, picture: String) {this.rutes[index].pictures.add(picture)}
    fun getPictures(index:Int):ArrayList<String> {return this.rutes[index].pictures}
    /*fun setExtra(index:Int,subIndex:Int,name:String,value:Double) {
        this.rutes[index].price.extras[subIndex].name=name
        this.rutes[index].price.extras[subIndex].value=value
    }*/
    fun getExtras(index:Int):List<ExtraFeatures> {return this.rutes[index].price.extras}

    override fun toString(): String {
        var sb = StringBuilder()

        var count = 0
        for (e in rutes){
            count++
            sb.append("Car nº$count:\n" +
                    "Brand:     ${e.model.brand}\n" +
                    "Model:     ${e.model.model}\n" +
                    "Color:     ${e.model.color}\n" +
                    "Engine:    ${e.engine.name}\n" +
                    "Fuel:      ${e.engine.fuel}\n" +
                    "ID:        ${e.id}" +
                    "Km:        ${e.kilometers}\n" +
                    "Price:     ${e.price.initialPrice}\n" +
                    "Offer:     ${e.price.offerPrice}\n" +
                    "Extras:\n")

            for (e in e.price.extras) {
                sb.append(" - ${e.name}: ${e.value}\n")
            }

            sb.append("------------------------------------\n\n")
        }

        return sb.toString()
    }

}