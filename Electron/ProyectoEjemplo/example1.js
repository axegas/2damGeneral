var userType = 1;
switch (userType) {
case 1:
case 2: // Types 1 and 2 enter here
console.log ("You can access this zone");
break;
case 3:
console.log ("You don't have permission to access here");
break;
default: // None of the above
console.error ("Bad user type!");
}