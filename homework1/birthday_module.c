/**
 * Brian Fekete
 * 2/23/17
 */ 
#include <linux/init.h>
#include <linux/kernel.h>
#include <linux/module.h>
#include <linux/printk.h>
#include <linux/slab.h> //kmalloc
#include <linux/list.h> // list
#include <linux/types.h> // struct list_head
MODULE_LICENSE("GPL");
MODULE_DESCRIPTION("Homework-1");
MODULE_AUTHOR("Brian Fekete");

struct birthday {
  int day;
  int month;
  int year;
  char *name;
  struct list_head list;
};

static LIST_HEAD(birthday_list); // LIST_HEAD object to reference head of the list

int birthday_module_init(void) {
  printk(KERN_INFO "Loading Module\n"); 
  // insert 4 nodes into linkedlist
  struct birthday *person;
  person = kmalloc(sizeof(*person) , GFP_KERNEL);
  person->month = 3;
  person->day = 16;
  person->year = 1751;
  person->name = "Madison";
  INIT_LIST_HEAD(&person->list);
  list_add_tail(&person->list, &birthday_list);

  person = kmalloc(sizeof(*person) , GFP_KERNEL);
  person->month = 2;
  person->day = 22;
  person->year = 1732;
  person->name = "Washington";
  INIT_LIST_HEAD(&person->list);
  list_add_tail(&person->list, &birthday_list);

  person = kmalloc(sizeof(*person) , GFP_KERNEL);
  person->month = 10;
  person->day = 30;
  person->year = 1735;
  person->name = "Adams";
  INIT_LIST_HEAD(&person->list);
  list_add_tail(&person->list, &birthday_list);

  person = kmalloc(sizeof(*person) , GFP_KERNEL);
  person->month = 4;
  person->day = 13;
  person->year = 1743;
  person->name = "Jefferson";
  INIT_LIST_HEAD(&person->list);
  list_add_tail(&person->list, &birthday_list);

  // Macro
  list_for_each_entry(person, &birthday_list, list) {
    // On each interation ptr points to the next birthday struct
    printk(KERN_INFO "Adding: %s (%d/%d/%d)\n", person->name, person->month, person->day, person->year);
  }
  
  return 0;
} 

void birthday_module_exit(void) {
  printk(KERN_INFO "Removing Module\n");
  // Traverse and output each node
  struct birthday *ptr, *next;
  list_for_each_entry_safe(ptr, next, &birthday_list, list) {
    printk(KERN_INFO "Removing: %s (%d/%d/%d)\n", ptr->name, ptr->month, ptr->day, ptr->year);
    // Deallocate list
    list_del(&ptr->list);
    kfree(ptr);
  }
  
  
}

module_init(birthday_module_init);
module_exit(birthday_module_exit);
