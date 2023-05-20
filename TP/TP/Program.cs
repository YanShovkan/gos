// See https://aka.ms/new-console-template for more information
using TP;

var functions = new Functions();
var list = functions.GenerateAbstrctobjects();
//var list = functions.LoadFromFile("text.xml");
//functions.SaveFile("text.xml", list);

functions.PrintList(list);

var result = list.Where(x => x.AbstractInt > 0).GroupBy(x => x.AbstractInt).Select(g => new Result { Id = g.Key, Count = g.Count() }).ToList();

foreach (var item in result)
{
    Console.WriteLine($"{item.Id} - количество {item.Count}");
}