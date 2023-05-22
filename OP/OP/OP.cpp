#include <iostream>

struct podrazdelenie {
    int number;
    const char* name;
    const char* specialization;
    int count;
    const char* FIO;
    podrazdelenie(int _number, const char* _name, const char* _specialization, int _count, const char* _FIO) {
        number = _number;
        name = _name;
        specialization = _specialization;
        count = _count;
        FIO = _FIO;
    }
};

struct node {
    podrazdelenie* object;
    node* next;
    node* prev;

    node(podrazdelenie* value){
        object = value;
        next = nullptr;
        prev = nullptr;
    }
};

struct list {
    node* head;
    node* tail;

    list() {
        head = nullptr;
        tail = nullptr;
    }

    void add(podrazdelenie* value) {
        node* new_node = new node(value);
        if (is_empty()) {
            tail = new_node;
            head = new_node;
        }
        else {
            node* prevNode = nullptr;
            node* currentNode = head;

            if (currentNode->object->number > value->number) {
                new_node->next = currentNode;
                new_node->prev = prevNode;
                head = new_node;
                currentNode->prev = new_node;
                return;
            }

            prevNode = currentNode;
            currentNode = currentNode->next;

            while (currentNode != nullptr) {
                if (currentNode->object->number > value->number) {
                    new_node->next = currentNode;
                    new_node->prev = prevNode;
                    currentNode->prev = new_node;
                    prevNode->next = new_node;
                    return;
                }
                else {
                    prevNode = currentNode;
                    currentNode = currentNode->next;
                }
            }

            new_node->next = currentNode;
            new_node->prev = prevNode;
            tail = new_node;
            prevNode->next = new_node;
        }
    }

    void print_list() {
        if (is_empty()) {
            printf("List is empty\n");
            return;
        }

        node* currentNode = head;
        int i = 1;
        printf("List:\n");
        while (currentNode != nullptr)
        {
            printf("Element %d - number: %d, name: %s, specialization: %s, count: %d, FIO:%s \n",i, currentNode->object->number, currentNode->object->name, currentNode->object->specialization, currentNode->object->count, currentNode->object->FIO);
            i++;
            currentNode = currentNode->next;
        }
    }

    bool is_empty() {
        return head == nullptr && tail == nullptr;
    }
};

int main()
{
    list myList = list();
    myList.print_list();
    podrazdelenie test1 = podrazdelenie(1, "test1", "test1", 10, "test1");
    podrazdelenie test2 = podrazdelenie(10, "test2", "test2", 10, "test2");
    podrazdelenie test3 = podrazdelenie(13, "test3", "test3", 10, "test3");
    podrazdelenie test4 = podrazdelenie(7, "test4", "test4", 10, "test4");
    myList.add(&test1);
    myList.print_list();
    myList.add(&test2);
    myList.print_list();
    myList.add(&test3);
    myList.print_list();
    myList.add(&test4);
    myList.print_list();
    myList.add(&test4);
    myList.print_list();
    myList.add(&test1);
    myList.print_list();
    myList.add(&test2);
    myList.print_list();
    myList.add(&test3);
    myList.print_list();
    myList.add(&test4);
    myList.print_list();
    myList.add(&test4);
    myList.print_list();

}
