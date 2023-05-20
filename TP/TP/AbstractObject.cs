using System.Xml.Serialization;

namespace TP;

[XmlRoot("elem")]
public class AbstractObject
{
    [XmlElement("digit")]
    public int AbstractInt { get; set; }

    [XmlElement("string")]
    public string AbstractString { get; set; }

    [XmlArray("list")]
    public List<object> AbstractList { get; set; }
}
