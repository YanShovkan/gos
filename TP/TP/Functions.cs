using System.Xml.Serialization;

namespace TP;
public class Functions
{
    XmlSerializer serializer = new XmlSerializer(typeof(List<AbstractObject>)); 
    Random random = new Random();
    public List<AbstractObject> GenerateAbstrctobjects()
    {
        return Enumerable.Range(0, 20).Select(i => new AbstractObject
        {
            AbstractInt = random.Next(3),
            AbstractString = Guid.NewGuid().ToString(),
            AbstractList = new() { Guid.NewGuid().ToString(), Guid.NewGuid(), random.Next() }
        }).ToList();
    }

    public void PrintList(List<AbstractObject> abstractObjects)
    {
        Console.WriteLine("Вывод списка");
        foreach(var abstractObject in abstractObjects)
        {
            Console.Write($"Элемент: {abstractObject.AbstractInt} {abstractObject.AbstractString}, список содержит: ");
            foreach(var item in abstractObject.AbstractList) 
            {
                Console.Write($"{abstractObject.AbstractInt} {abstractObject.AbstractString}");
            }
            Console.WriteLine();
        }
    }

    public void SaveFile(string filename, List<AbstractObject> abstractObjects)
    {
        using (FileStream fs = new FileStream(filename, FileMode.Create))
        {
            serializer.Serialize(fs, abstractObjects);
        }
    }

    public List<AbstractObject> LoadFromFile(string filename)
    {
        using (FileStream fs = new FileStream(filename, FileMode.Open))
        {
            return (List<AbstractObject>)serializer.Deserialize(fs)!;
        }
    }
}
