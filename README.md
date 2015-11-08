# jcompiler

Java-based language compiler which generates MAPL assembler code.

## Example

### Input
```java
/**
 * Test program.
 */

double[10][5] v;
double x;
char z;

void main() {
 int a,b;
 read b;
 a = b+3;
 write a;
}
```

### Output

```assembly
#source	"inputs/cg.mini.input.txt"

	' * Global variables
	' * [10,[5,double] v (offset 0)] 
	' * double x (offset 200)
	' * char z (offset 204)

call main
halt

#line	9

 main:
	' * Parameters
	' * Local variables
	' * integer a (offset -2)
	' * integer b (offset -4)
	enter	4

#line	11
	' * Reading
	push	bp
	pushi	-4
	addi
	ini
	storei

#line	12
	' * Assignment
	push	bp
	pushi	-2
	addi
	push	bp
	pushi	-4
	addi
	loadi
	pushi	3
	addi
	storei

#line	13
	' * Writing
	push	bp
	pushi	-2
	addi
	loadi
	outi

	ret	0, 4, 0
	
```

