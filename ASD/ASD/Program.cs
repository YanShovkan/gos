// See https://aka.ms/new-console-template for more information
using ASD;

Console.WriteLine("Hello, World!");

Sorts sorts = new Sorts();
var array = sorts.FillArray(20);
sorts.PrintArray(array);

sorts.GnomeSort(array);

sorts.PrintArray(array);