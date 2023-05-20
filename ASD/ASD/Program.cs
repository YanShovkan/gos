using ASD;

//Sorts sorts = new Sorts();
//var array = sorts.FillArray(20);
//sorts.PrintArray(array);

//sorts.GnomeSort(array);

//sorts.PrintArray(array);

var hashTable = new HashTableDoubleHash(10);
for(int i = 1; i < 20; i++)
{
    hashTable.Insert(new PersonModel { Age = i, Name = "Ян" });
}

hashTable.Delete(new PersonModel { Age = 3, Name = "Ян" });
hashTable.Insert(new PersonModel { Age = 55, Name = "Ян" });
hashTable.Print();


for (int i = 1; i < 20; i++)
{
    var model = new PersonModel { Age = i, Name = "Ян" };
    Console.WriteLine($"Поиск элемента {model.Name} - {model.Age} : {hashTable.Find(model)}");
}

