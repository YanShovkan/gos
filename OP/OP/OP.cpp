#include <iostream>

struct plane {
    int number;
    const char* type;
    const char* model;
    int gruz;
    int dalnost;
    plane(int _number, const char* _type, const char* _model, int _gruz, int _dalnost) {
        number = _number;
        type = _type;
        model = _model;
        gruz = _gruz;
        dalnost = _dalnost;
    }
};

struct node {
    plane* object;
    node* next;
    node* prev;

    node(plane* value){
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

    void add(plane* value) {
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
            printf("Element %d - number: %d, type: %s, model: %s, gruz: %d, dalnost:%d \n",i, currentNode->object->number, currentNode->object->type, currentNode->object->model, currentNode->object->gruz, currentNode->object->dalnost);
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
    plane test1 = plane(5, "pass", "11", 10, 100);
    plane test2 = plane(4, "gruz", "22", 10, 200);
    plane test3 = plane(23, "test", "33", 10, 300);
    plane test4 = plane(71, "test", "44", 10, 400);
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
